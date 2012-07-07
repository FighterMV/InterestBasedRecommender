/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper.impl;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.SVDSimilarityCalculator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SVDSimilarityCalculatorImpl implements SVDSimilarityCalculator{

    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> personDTOs, int numberOfUsersToReturn) {
	
	Matrix queryDocument = getMatrix(person.getPersonInterestKeywords(), personDTOs);
	Matrix A = getA(person.getPersonInterestKeywords(), personDTOs);
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
	int firstOccurenceOfZeroInS = 1;
	double[] singularValues = svd.getSingularValues();
	for(int i = 0; i < singularValues.length; i++){
	    if(singularValues[i] < 0.01){
		return i + 1;
	    }
	}
	return firstOccurenceOfZeroInS;
    }
    
    
    private Matrix getMatrix(List<String> keywords, List<PersonDTO> persons){
		
	List<String> neededTerms = getNeededTerms(keywords, persons);
	int numberOfNeededRows = neededTerms.size();
	
	double[][] matrixArray = new double[numberOfNeededRows][1];
	
	int i = 0;
	for(String term : neededTerms){
	    int numberOfOccurencesInList = getNumberOfOccurences(term, keywords);
	    matrixArray[i][0] = numberOfOccurencesInList;
	    i++;
	}
	
	return new Matrix(matrixArray);
    }
    
    private List<String> getNeededTerms(List<String> keywords, List<PersonDTO> persons){
	List<String> neededTermList = new ArrayList<String>();
	List<String> allKeywords = new ArrayList<String>();
	allKeywords.addAll(keywords);
	for(PersonDTO person : persons){
	    allKeywords.addAll(person.getPersonInterestKeywords());
	}
	for(String keyword : allKeywords){
	    if(!neededTermList.contains(keyword)){
		 neededTermList.add(keyword);
	    }
	}
	return neededTermList;
    }
    
    private int getNumberOfOccurences(String term, List<String> keywords){
	int numberOfOccurences = 0;
	for(String keyword : keywords){
	    if(term.equals(keyword)){
		numberOfOccurences++;
	    }
	}
	return numberOfOccurences;
    }
    
    private Matrix getA(List<String> keyWords, List<PersonDTO> persons){
	
	List<String> keyWordsToSearchFor = getNeededTerms(keyWords, persons);
	int numberOfKeywords = keyWordsToSearchFor.size();
	
	double[][] A = new double[numberOfKeywords][persons.size()];
	
	int i = 0;
	for(PersonDTO person : persons){
	    int j = 0;
	    for(String keyWord : keyWordsToSearchFor){
		int numberOfOccurences = getNumberOfOccurences(keyWord, person.getPersonInterestKeywords());
		A[j][i] = numberOfOccurences;
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
	int[] indexesToReturn = new int[numberOfIndexesToReturn];
	
	for(int i = 0; i < similarities.length; i++){
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
