/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper.impl;

import Jama.Matrix;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.CosineCalculator;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class CosineCalculatorImpl<E> implements CosineCalculator{
    
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
    public double getAngle(List interestingKeywords1, List interestingKeywords2) {
	Set<String> neededKeywords = new HashSet<String>();
	neededKeywords.addAll(interestingKeywords1);
	neededKeywords.addAll(interestingKeywords2);
	
	double[][] interestVectorOfPerson = getInterestVector(interestingKeywords1, neededKeywords);
	double[][] interestVectorOfCandidate = getInterestVector(interestingKeywords2, neededKeywords);
	
	Matrix vectorOfPerson = new Matrix(interestVectorOfPerson);
	Matrix vectorOfCandidate = new Matrix(interestVectorOfCandidate);
	
	double cosine = getCosine(vectorOfPerson, vectorOfCandidate);
	
	Double angle = Math.acos(cosine);
	
	return angle;
    }

    private double[][] getInterestVector(List<String> interestingKeywords, Set<String> neededKeywords) {
	double[][] vector = new double[neededKeywords.size()][1];
	
	int i = 0;
	for(String keyword : neededKeywords){
	    double numberOfOccurences = 0.0;
	    for(String interest : interestingKeywords){
		if(interest.equals(keyword)){
		    numberOfOccurences += 1;
		}
	    }
	    vector[i][0] = numberOfOccurences;
	    i++;
	}
	
	return vector;
    }

    @Override
    public List<E> getXMostSimilarObjects(Map angleMap, int numberOfObjectsToReturn) {
	List<E> bestObjects = new ArrayList<E>();
	for (Iterator<E> it = angleMap.keySet().iterator(); it.hasNext();) {
	    E candidate = it.next();
	    if(bestObjects.size() <= numberOfObjectsToReturn){
		bestObjects.add(candidate);
	    }else{
		E weakestObjectInList = getWeakestPersonInList(bestObjects, angleMap);
		Double candidateAngle = (Double) angleMap.get(candidate);
		Double weakestObjectInListAngle = (Double) angleMap.get(weakestObjectInList);
		if(candidateAngle < weakestObjectInListAngle){
		    bestObjects.remove(weakestObjectInList);
		    bestObjects.add(candidate);
		}
	    }
	}
	
	return bestObjects;
    }
    
    private E getWeakestPersonInList(List objects, Map angleMap){
	if(objects.isEmpty()){
	    return null;
	}
	E weakestObject = (E) objects.get(0);
	
	for (Iterator<E> it = objects.iterator(); it.hasNext();) {
	    E object = it.next();
	    if((Double) angleMap.get(object) > (Double) angleMap.get(weakestObject)){
		weakestObject = object;
	    }
	}
	
	return weakestObject;
	
    }
    
}
