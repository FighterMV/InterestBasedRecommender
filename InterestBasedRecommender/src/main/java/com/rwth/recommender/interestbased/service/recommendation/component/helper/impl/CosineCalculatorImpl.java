/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper.impl;

import Jama.Matrix;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineCalculator;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class CosineCalculatorImpl implements CosineCalculator{
    
    private double getCosine(Matrix vector1, Matrix vector2) {
	if(vector1.getRowDimension() != vector2.getRowDimension() || vector1.getColumnDimension() != 1 || vector2.getColumnDimension() != 1){
	    throw new IllegalStateException("vectors have got illegal size");
	}
	
	double[][] personArray = vector1.getArray();
	double[][] candidateArray = vector2.getArray();
	
	double topStatement = 0.0;
	for(int i = 0; i < personArray.length; i++){
	    topStatement += personArray[i][0] * candidateArray[i][0];
	}
	
	double personNorm = 1.0;
	double candidateNorm = 1.0;
	for(int i = 0; i < personArray.length; i++){
	    personNorm = personNorm + (candidateArray[i][0] * candidateArray[i][0]);
	    candidateNorm = candidateNorm + (candidateArray[i][0] * candidateArray[i][0]);
	}
	personNorm = Math.sqrt(personNorm);
	candidateNorm = Math.sqrt(candidateNorm);
	
	double bottomStatement = personNorm * candidateNorm;
	
	double cosine = topStatement / bottomStatement;
	
	return cosine;
    }

    @Override
    public double getInterestsAngle(List<PersonInterestDTO> interests1, List<PersonInterestDTO> interests2) {
	Set<String> neededKeywords = new HashSet<String>();
	
	for(PersonInterestDTO personInterest : interests1){
	    neededKeywords.add(personInterest.getInterest().getName());
	}
	
	for(PersonInterestDTO personInterest : interests2){
	    neededKeywords.add(personInterest.getInterest().getName());
	}
	
	double[][] interestVectorOfPerson = getInterestVector(interests1, neededKeywords);
	double[][] interestVectorOfCandidate = getInterestVector(interests2, neededKeywords);
	
	Matrix vectorOfPerson = new Matrix(interestVectorOfPerson);
	Matrix vectorOfCandidate = new Matrix(interestVectorOfCandidate);
	
	double cosine = getCosine(vectorOfPerson, vectorOfCandidate);
	
	Double angle = Math.acos(cosine);
	
	return angle;
    }

    private double[][] getInterestVector(List<PersonInterestDTO> personInterests, Set<String> neededKeywords) {
	double[][] vector = new double[neededKeywords.size()][1];
	
	int i = 0;
	for (Iterator<String> it = neededKeywords.iterator(); it.hasNext();) {
	    String keyWord = it.next();
	    double vectorEntryForInterest = 0.0;
	    for(PersonInterestDTO personInterest  : personInterests){
		if(personInterest.getInterest().getName().equals(keyWord)){
		    vectorEntryForInterest += personInterest.getWeighting();
		}
	    }
	    vector[i][0] = vectorEntryForInterest;
	    i++;
	}
	
	return vector;
    }
    
    private double[][] getKeywordVector(List<String> interests, Set<String> neededKeywords) {
	double[][] vector = new double[neededKeywords.size()][1];
	
	int i = 0;
	for (Iterator<String> it = neededKeywords.iterator(); it.hasNext();) {
	    String keyWord = it.next();
	    double vectorEntryForInterest = 0.0;
	    for(String interest  : interests){
		if(interest.equals(keyWord)){
		    vectorEntryForInterest += 1;
		}
	    }
	    vector[i][0] = vectorEntryForInterest;
	    i++;
	}
	
	return vector;
    }

    @Override
    public double getKeywordsAngle(List<String> keywords1, List<String> keywords2) {
	Set<String> neededKeywords = new HashSet<String>();
	
	neededKeywords.addAll(keywords1);
	neededKeywords.addAll(keywords2);
	
	double[][] interestVectorOfPerson = getKeywordVector(keywords1, neededKeywords);
	double[][] interestVectorOfCandidate = getKeywordVector(keywords2, neededKeywords);
	
	Matrix vectorOfPerson = new Matrix(interestVectorOfPerson);
	Matrix vectorOfCandidate = new Matrix(interestVectorOfCandidate);
	
	double cosine = getCosine(vectorOfPerson, vectorOfCandidate);
	
	Double angle = Math.acos(cosine);
	
	return angle;
    }

    
    
}
