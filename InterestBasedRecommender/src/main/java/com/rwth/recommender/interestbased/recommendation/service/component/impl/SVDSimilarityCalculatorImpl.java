/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.SVDSimilarityCalculator;
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
    public Boolean areSimilar(PersonDTO person1, PersonDTO person2) {
	List<String> person1InterestKeywords = person1.getPersonInterestKeywords();
	List<String> person2InterestKeywords = person2.getPersonInterestKeywords();
	
	Matrix matrix = getMatrix(person1InterestKeywords, person2InterestKeywords);
	
	SingularValueDecomposition svd = matrix.svd();
	
	return false;
    }
    
    
    private Matrix getMatrix(List<String> keywords1, List<String> keywords2){
		
	Set<String> neededTerms = getNeededTerms(keywords1, keywords2);
	int numberOfNeededRows = neededTerms.size();
	
	double[][] matrixArray = new double[numberOfNeededRows][2];
	
	int i = 0;
	for(String term : neededTerms){
	    int numberOfOccurencesInList1 = getNumberOfOccurences(term, keywords1);
	    int numberOfOccurencesInList2 = getNumberOfOccurences(term, keywords2);
	    matrixArray[i][0] = numberOfOccurencesInList1;
	    matrixArray[i][1] = numberOfOccurencesInList2;
	    i++;
	}
	
	return new Matrix(matrixArray);
    }
    
    private Set<String> getNeededTerms(List<String> keywords1, List<String> keywords2){
	Set<String> combinedSet = new HashSet<String>();
	combinedSet.addAll(keywords1);
	combinedSet.addAll(keywords2);	
	return combinedSet;
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
}
