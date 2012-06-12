/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.SVDSimilarityCalculator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SVDSimilarityCalculatorImpl implements SVDSimilarityCalculator{

    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> personDTOs, int numberOfUsersToReturn) {
	
	Matrix queryDocument = getMatrix(person.getPersonInterestKeywords());
	Matrix A = getA(person.getPersonInterestKeywords(), personDTOs);
	SingularValueDecomposition svd = A.svd();
	
	Matrix U = getFirstXColums(svd.getU(), Constants.DIMENSIONS_TO_CHECK_SIMILARITY);
	Matrix V = getFirstXColums(svd.getV(), Constants.DIMENSIONS_TO_CHECK_SIMILARITY);
	Matrix S = getFirstColumsAndRows(svd.getS(), Constants.DIMENSIONS_TO_CHECK_SIMILARITY, Constants.DIMENSIONS_TO_CHECK_SIMILARITY);
	
	Matrix queryMatrix = (queryDocument.transpose().times(U)).times(S.inverse());
	
	Double[] similarities = getSimilarities(queryMatrix, V);
	
	int[] indexOfXMostSimilarDocuments = getXSimilarDocumentIndexes(similarities, numberOfUsersToReturn);
	
	List<PersonDTO> similarPersons = new ArrayList<PersonDTO>();
	for(int i = 0; i < indexOfXMostSimilarDocuments.length; i++){
	    similarPersons.add(personDTOs.get(indexOfXMostSimilarDocuments[i]));
	}
	
	return similarPersons;
    }
    
    
    private Matrix getMatrix(List<String> keywords){
		
	List<String> neededTerms = getNeededTerms(keywords);
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
    
    private List<String> getNeededTerms(List<String> keywords){
	List<String> neededTermList = new ArrayList<String>();
	for(String keyword : keywords){
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
	
	List<String> keyWordsToSearchFor = getNeededTerms(keyWords);
	int numberOfKeywords = keyWordsToSearchFor.size();
	
	double[][] A = new double[persons.size()][numberOfKeywords];
	
	int i = 0;
	for(PersonDTO person : persons){
	    int j = 0;
	    for(String keyWord : keyWordsToSearchFor){
		int numberOfOccurences = getNumberOfOccurences(keyWord, person.getPersonInterestKeywords());
		A[i][j] = numberOfOccurences;
		j++;
	    }
	    i++;
	}
		
	return new Matrix(A);
    }
    
    
    private Matrix getFirstXColums(Matrix matrix, int numberOfColums){
	return matrix.getMatrix(0, matrix.getRowDimension()-1, 0, numberOfColums-1);
    }
    
    private Matrix getFirstColumsAndRows(Matrix matrix, int numberOfColums, int numberOfRows){
	return matrix.getMatrix(0, numberOfColums-1, 0, numberOfRows-1);
    }
    
    private Double[] getSimilarities(Matrix queryMatrix, Matrix documentsMatrix){
	
	int numberOfDocuments = documentsMatrix.getRowDimension();
	Double[] similarities = new Double[numberOfDocuments];
	
	for(int currenDocumentIndex = 0; currenDocumentIndex < numberOfDocuments; currenDocumentIndex++){
	    
	    Matrix documentMatrix = documentsMatrix.getMatrix(currenDocumentIndex, currenDocumentIndex, 0, documentsMatrix.getColumnDimension()-1);
	    
	    double numerator = 0;	
	    for(int i = 0; i < queryMatrix.getColumnDimension(); i++){
		numerator += queryMatrix.get(0, i) * documentsMatrix.get(0, i);
	    }


	    double queryMatrixSquareSum = 0;
	    for(int i = 0; i < queryMatrix.getColumnDimension(); i++){
		queryMatrixSquareSum += queryMatrix.get(0, i) * queryMatrix.get(0, i);
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
		for(int j = 0; j < numberOfIndexesToReturn; j++){
		    if(similarities[i] > similarities[indexesToReturn[j]]){
			indexesToReturn[j] = i;
		    }
		}
	    }
	}
	    
	return indexesToReturn;
    }

}
