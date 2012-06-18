/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper.impl;

import Jama.Matrix;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.CosineSimilarPersonFinder;
import java.util.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class CosineSimilarPersonFinderImpl implements CosineSimilarPersonFinder{

    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn) {
	
	Map<PersonDTO, Double> candidateAngleMap = new HashMap<PersonDTO, Double>();
	
	for(PersonDTO candidate : persons){
	    Double angle = getAngle(person, candidate);
	    candidateAngleMap.put(candidate, angle);
	}
	
	List<PersonDTO> mostSimilarPersons = getXMostSimilarPersons(candidateAngleMap, numberOfPersonsToReturn);
	
	return mostSimilarPersons;
    }
    
    
    private Double getAngle(PersonDTO person, PersonDTO candidate){
	
	Set<String> neededKeywords = new HashSet<String>();
	neededKeywords.addAll(person.getPersonInterestKeywords());
	neededKeywords.addAll(candidate.getPersonInterestKeywords());
	
	double[][] interestVectorOfPerson = getInterestVector(person, neededKeywords);
	double[][] interestVectorOfCandidate = getInterestVector(candidate, neededKeywords);
	
	Matrix vectorOfPerson = new Matrix(interestVectorOfPerson);
	Matrix vectorOfCandidate = new Matrix(interestVectorOfCandidate);
	
	double cosine = getCosine(vectorOfPerson, vectorOfCandidate);
	
	Double angle = Math.acos(cosine);
	
	return angle;
    }
    
    
    private double[][] getInterestVector(PersonDTO person, Set<String> neededKeywords){
	
	double[][] vector = new double[neededKeywords.size()][1];
	
	int i = 0;
	for(String keyword : neededKeywords){
	    double numberOfOccurences = 0.0;
	    for(String interest : person.getPersonInterestKeywords()){
		if(interest.equals(keyword)){
		    numberOfOccurences += 1;
		}
	    }
	    vector[i][0] = numberOfOccurences;
	    i++;
	}
	
	return vector;
	
    }
    
    
    private List<PersonDTO> getXMostSimilarPersons(Map<PersonDTO, Double> candidateAngleMap, int numberOfPersonsToReturn){
	List<PersonDTO> bestPersons = new ArrayList<PersonDTO>();
	
	for(PersonDTO candidate : candidateAngleMap.keySet()){
	    if(bestPersons.size() <= numberOfPersonsToReturn){
		bestPersons.add(candidate);
	    }else{
		PersonDTO weakestPersonInList = getWeakestPersonInList(bestPersons, candidateAngleMap);
		Double candidateAngle = candidateAngleMap.get(candidate);
		Double weakestPersonInListAngle = candidateAngleMap.get(weakestPersonInList);
		if(candidateAngle < weakestPersonInListAngle){
		    bestPersons.remove(weakestPersonInList);
		    bestPersons.add(candidate);
		}
	    }
	}
	
	return bestPersons;
    }
    
    private PersonDTO getWeakestPersonInList(List<PersonDTO> persons, Map<PersonDTO, Double> candidateAngleMap){
	if(persons.isEmpty()){
	    return null;
	}
	PersonDTO weakestPerson = persons.get(0);
	
	for(PersonDTO person : persons){
	    if(candidateAngleMap.get(person) > candidateAngleMap.get(weakestPerson)){
		weakestPerson = person;
	    }
	}
	
	return weakestPerson;
	
    }
    
    
    private double getCosine(Matrix personVector, Matrix candidateVector){
	if(personVector.getRowDimension() != candidateVector.getRowDimension() || personVector.getColumnDimension() != 1 || candidateVector.getColumnDimension() != 1){
	    throw new IllegalStateException("vectors have got illegal size");
	}
	
	double[][] personArray = personVector.getArray();
	double[][] candidateArray = candidateVector.getArray();
	
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
    
    
}
