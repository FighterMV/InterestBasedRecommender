/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper.impl;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.SVDSimilarityCalculator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SVDSimilarityCalculatorImpl implements SVDSimilarityCalculator{
    
    @Autowired
    PersonInterestService personInterestService;

    @Override
    public List<PersonDTO> getXSimilarPersonsByGroup(PersonDTO person, List<PersonDTO> personDTOs, int numberOfUsersToReturn) {
	return getXSimilarPersonsCalc(person, personDTOs, numberOfUsersToReturn, true);
    }
    
    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> personDTOs, int numberOfUsersToReturn) {
	return getXSimilarPersonsCalc(person, personDTOs, numberOfUsersToReturn, false);
    }
    
    public List<PersonDTO> getXSimilarPersonsCalc(PersonDTO person, List<PersonDTO> personDTOs, int numberOfUsersToReturn, Boolean byGroup) {
	
	if(personDTOs.isEmpty()){
	    return personDTOs;
	}
	
	Matrix queryDocument = getMatrix(person, personDTOs, byGroup);
	
	Matrix A = getA(person, personDTOs, byGroup);
	
	SingularValueDecomposition svd = A.svd();
	
	int dimensionsToCheckSimilarity = getDimensionToCheckSimilarity(svd);
	
	Matrix U = getFirstXColums(svd.getU(), dimensionsToCheckSimilarity);
	Matrix V = getFirstXColums(svd.getV(), dimensionsToCheckSimilarity);
	Matrix S = getFirstColumsAndRows(svd.getSingularValues(), dimensionsToCheckSimilarity, dimensionsToCheckSimilarity);
	
	Matrix queryMatrix = (queryDocument.transpose().times(U)).times(S.inverse());
	
	Double[] similarities = getSimilarities(queryMatrix, V);
	
	int[] indexOfXMostSimilarDocuments = getXSimilarDocumentIndexes(similarities, numberOfUsersToReturn);
	
	List<PersonDTO> similarPersons = new ArrayList<PersonDTO>();
	for(int i = 0; i < indexOfXMostSimilarDocuments.length; i++){
	    similarPersons.add(personDTOs.get(indexOfXMostSimilarDocuments[i]));
	}
	
	return similarPersons;
    }

    private int getDimensionToCheckSimilarity(SingularValueDecomposition svd) {
	int dimensionsToCheckSimilarity = Constants.MAX_DIMENSIONS_TO_CHECK_SIMILARITY;
	dimensionsToCheckSimilarity = Math.min(dimensionsToCheckSimilarity, svd.getU().getColumnDimension());
	dimensionsToCheckSimilarity = Math.min(dimensionsToCheckSimilarity, svd.getV().getColumnDimension());
	dimensionsToCheckSimilarity = Math.min(dimensionsToCheckSimilarity, svd.getSingularValues().length);
	int firstOccurenceOfZeroInS = getFirstOccurenceOfZeroInS(svd);
	dimensionsToCheckSimilarity = Math.min(dimensionsToCheckSimilarity, firstOccurenceOfZeroInS);
	return dimensionsToCheckSimilarity;
    }

    private int getFirstOccurenceOfZeroInS(SingularValueDecomposition svd) {
	int firstOccurenceOfZeroInS = svd.getSingularValues().length;
	double[] singularValues = svd.getSingularValues();
	for(int i = 0; i < singularValues.length; i++){
	    if(singularValues[i] < 0.01){
		return i + 1;
	    }
	}
	return firstOccurenceOfZeroInS;
    }
    
    
    private Matrix getMatrix(PersonDTO personDTO, List<PersonDTO> persons, Boolean byGroup){
		
	List<String> neededTerms = getNeededTerms(personDTO, persons, byGroup);
	int numberOfNeededRows = neededTerms.size();
	
	double[][] matrixArray = new double[numberOfNeededRows][1];
	
	int i = 0;
	for(String term : neededTerms){
	    int weighting = getWeighting(term, personDTO, byGroup);
	    matrixArray[i][0] = weighting;
	    i++;
	}
	
	return new Matrix(matrixArray);
    }
    
    private List<String> getNeededTerms(PersonDTO personDTO, List<PersonDTO> persons, Boolean byGroup){
	List<String> neededTermList = new ArrayList<String>();
	List<String> allKeywords = new ArrayList<String>();
	List<String> keywords;
	if(byGroup){
	    keywords = personDTO.getPersonMainTopics();
	}else{
	    List<PersonInterestDTO> personInterests = personInterestService.getPersonInterests(personDTO);
	    keywords = new ArrayList<String>();
	    for(PersonInterestDTO personInterestDTO : personInterests){
		keywords.add(personInterestDTO.getInterest().getName());
	    }
	}
	allKeywords.addAll(keywords);
	for(PersonDTO person : persons){
	    if(byGroup){
		allKeywords.addAll(person.getPersonMainTopics());
	    }else{
		List<PersonInterestDTO> personInterests = personInterestService.getPersonInterests(person);
		for(PersonInterestDTO personInterestDTO : personInterests){
		    allKeywords.add(personInterestDTO.getInterest().getName());
		}
	    }
	}
	for(String keyword : allKeywords){
	    if(!neededTermList.contains(keyword)){
		 neededTermList.add(keyword);
	    }
	}
	return neededTermList;
    }
    
    private int getWeighting(String term, PersonDTO personDTO, Boolean byGroup){
	if(byGroup){
	    for(String mainTopic : personDTO.getPersonMainTopics()){
		if(mainTopic.equals(term)){
		    return 1;
		}
	    }
	}else{
	    List<PersonInterestDTO> personInterests = personInterestService.getPersonInterests(personDTO);
	    for(PersonInterestDTO personInterestDTO : personInterests){
		if(personInterestDTO.getInterest().getName().equals(term)){
		    return personInterestDTO.getWeighting();
		}
	    }
	}
	return 0;
    }
    
    private Matrix getA(PersonDTO personDTO, List<PersonDTO> persons, Boolean byGroup){
		
	List<String> keyWordsToSearchFor = getNeededTerms(personDTO, persons, byGroup);
	int numberOfKeywords = keyWordsToSearchFor.size();
	
	double[][] A = new double[numberOfKeywords][persons.size()];
	
	int i = 0;
	for(PersonDTO person : persons){
	    int j = 0;
	    for(String keyWord : keyWordsToSearchFor){
		int weighting = getWeighting(keyWord, personDTO, byGroup);
		A[j][i] = weighting;
		j++;
	    }
	    i++;
	}
		
	return new Matrix(A);
    }
    
    
    private Matrix getFirstXColums(Matrix matrix, int numberOfColums){
	return matrix.getMatrix(0, matrix.getRowDimension()-1, 0, numberOfColums-1);
    }
    
    private Matrix getFirstColumsAndRows(double[] singluarValues, int numberOfColums, int numberOfRows){
	int dimension = Math.min(singluarValues.length, numberOfRows);
	dimension = Math.min(dimension, numberOfColums);
	double[][] matrix = new double[dimension][dimension];
	for(int i = 0; i < dimension; i++){
	    for(int j = 0; j < dimension; j++){
		matrix[i][j] = 0;
	    }
	}
	for(int i = 0; i < dimension; i++){
	    matrix[i][i] = singluarValues[i];
	}
	return new Matrix(matrix);
    }
    
    private Double[] getSimilarities(Matrix queryMatrix, Matrix documentsMatrix){
	
	int numberOfDocuments = documentsMatrix.getRowDimension();
	Double[] similarities = new Double[numberOfDocuments];
	
	for(int currenDocumentIndex = 0; currenDocumentIndex < numberOfDocuments; currenDocumentIndex++){
	    
	    Matrix documentMatrix = documentsMatrix.getMatrix(currenDocumentIndex, currenDocumentIndex, 0, documentsMatrix.getColumnDimension()-1);
	    
	    double numerator = 0;	
	    for(int i = 0; i < queryMatrix.getColumnDimension(); i++){
		numerator += queryMatrix.get(0, i) * documentMatrix.get(0, i);
	    }


	    double queryMatrixSquareSum = 0;
	    for(int i = 0; i < queryMatrix.getColumnDimension(); i++){
		queryMatrixSquareSum += (queryMatrix.get(0, i) * queryMatrix.get(0, i));
	    }

	    double denominator = Math.sqrt(queryMatrixSquareSum);
	    
	    double documentMatrixSquareSum = 0;
	    for(int i = 0; i < documentMatrix.getColumnDimension(); i++){
		documentMatrixSquareSum += documentMatrix.get(0, i) * documentMatrix.get(0, i);
	    }
	    
	    denominator = denominator * Math.sqrt(documentMatrixSquareSum);
	    
	    double similarity = numerator / denominator;
	    
	    similarities[currenDocumentIndex] = similarity;
	}
	
	return similarities;
    }
    
    
    private int[] getXSimilarDocumentIndexes(Double[] similarities, int numberOfIndexesToReturn){
	numberOfIndexesToReturn = Math.min(similarities.length, numberOfIndexesToReturn);
	int[] indexesToReturn = new int[numberOfIndexesToReturn];
	
	for(int i = 0; i < similarities.length; i++){
	    if(similarities[i] >= Constants.MINIMUM_ANGEL_TO_BE_EQUAL_PERSON){
		if(i < numberOfIndexesToReturn){
		    indexesToReturn[i] = i;
		}else{
		    int smallestNumberIndexInSimilarities = getSmallestContentIndex(indexesToReturn, similarities);
		    int indexToReplace = 0;
		    for(int j = 0; j < indexesToReturn.length; j++){
			if(indexesToReturn[j] == smallestNumberIndexInSimilarities){
			    indexToReplace = j;
			}
		    }
		    if(similarities[i] > similarities[smallestNumberIndexInSimilarities]){
			indexesToReturn[indexToReplace] = i;
		    }
		}
	    }
	}
	    
	return indexesToReturn;
    }
    
    
    private int getSmallestContentIndex(int[] indexesToCheck, Double[] numbers){
	double[] numbersToCheck = new double[indexesToCheck.length];
	for(int i = 0; i < indexesToCheck.length; i++){
	    numbersToCheck[i] = numbers[indexesToCheck[i]];
	}
	int smallestNumberIndex = 0;
	for(int i = 0; i < numbersToCheck.length; i++){
	    if(numbersToCheck[i] < numbersToCheck[smallestNumberIndex]){
		smallestNumberIndex = i;
	    }
	}
	
	return indexesToCheck[smallestNumberIndex];
    }

}
