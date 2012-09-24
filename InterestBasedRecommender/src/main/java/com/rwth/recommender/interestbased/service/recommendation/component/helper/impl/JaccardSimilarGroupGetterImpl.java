/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper.impl;

import Jama.Matrix;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.JaccardSimilarGroupGetter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component
public class JaccardSimilarGroupGetterImpl implements JaccardSimilarGroupGetter{

    @Override
    public List<PersonDTO> getXSimilarPersonsByGroup(List<PersonDTO> persons, PersonDTO person, int numberOfPersonsToReturn) {
        Matrix vectorOfPerson = getVectorOfPerson(person, persons);
        List<Matrix> personsMatrix = getPersonsMatrix(person, persons);
        
        List<Double> similarities = new ArrayList<Double>();
                
        for(Matrix personMatrix : personsMatrix){
            double[][] personArray = vectorOfPerson.getArray();
            double[][] similarPersonArray = personMatrix.getArray();
            
            Double numberOfSimilarOnes = 0.0;
            
            for(int i = 0; i < vectorOfPerson.getRowDimension(); i++){
                if(personArray[i][0] == 1 && similarPersonArray[i][0] == 1){
                    numberOfSimilarOnes += 1.0;
                }
            }
            
            similarities.add(numberOfSimilarOnes / vectorOfPerson.getRowDimension());
            
        }
        
        return getXMostSimilarPersons(similarities, persons, numberOfPersonsToReturn);
    }
    
    private Matrix getVectorOfPerson(PersonDTO personDTO, List<PersonDTO> persons){
		
	List<String> neededTerms = getNeededTerms(personDTO, persons);
	int numberOfNeededRows = neededTerms.size();
	
	double[][] matrixArray = new double[numberOfNeededRows][1];
	
	int i = 0;
	for(String term : neededTerms){
            if(personDTO.getPersonMainTopics().contains(term)){
                matrixArray[i][0] = 1;
            }else{
                matrixArray[i][0] = 0;
            }
	    i++;
	}
	
	return new Matrix(matrixArray);
    }
    
    private List<Matrix> getPersonsMatrix(PersonDTO personDTO, List<PersonDTO> persons){
	List<Matrix> matrixOfPersons = new ArrayList<Matrix>();	
	List<String> keyWordsToSearchFor = getNeededTerms(personDTO, persons);
	int numberOfKeywords = keyWordsToSearchFor.size();
		
	for(PersonDTO person : persons){
            double[][] personMatrix = new double[numberOfKeywords][1];
            int i = 0;
	    for(String keyWord : keyWordsToSearchFor){
		if(person.getPersonMainTopics().contains(keyWord)){
                    personMatrix[i][0] = 1;
                }else{
                    personMatrix[i][0] = 0;
                }
                i++;
	    }
            matrixOfPersons.add(new Matrix(personMatrix));
	}
		
	return matrixOfPersons;
    }
    
    private List<String> getNeededTerms(PersonDTO personDTO, List<PersonDTO> persons){
	List<String> neededTermList = new ArrayList<String>();
	List<String> allKeywords = new ArrayList<String>();
	List<String> keywords = personDTO.getPersonMainTopics();	        
	allKeywords.addAll(keywords);
        
	for(PersonDTO person : persons){
            allKeywords.addAll(person.getPersonMainTopics());	    
	}
        
	for(String keyword : allKeywords){
	    if(!neededTermList.contains(keyword)){
		 neededTermList.add(keyword);
	    }
	}
	return neededTermList;
    }
    
    
    private List<PersonDTO> getXMostSimilarPersons(List<Double> similarities, List<PersonDTO> persons, int numberOfPersonsToReturn){
        numberOfPersonsToReturn = Math.min(persons.size(), numberOfPersonsToReturn);
        List<PersonDTO> mostSimilarPersons = new ArrayList<PersonDTO>();
        
        Map<PersonDTO, Double> personSimilarities = new HashMap<PersonDTO, Double>();
        for(int i = 0; i < persons.size(); i++){
            personSimilarities.put(persons.get(i), similarities.get(i));
        }
        
        for(PersonDTO currentPerson : personSimilarities.keySet()){
            if(mostSimilarPersons.size() < numberOfPersonsToReturn){
                mostSimilarPersons.add(currentPerson);
            }else{
                Double currentSimilarity = personSimilarities.get(currentPerson);
                PersonDTO lowestSimilarityPerson = getSmallestSimilarityPerson(mostSimilarPersons, personSimilarities);
                if(currentSimilarity > personSimilarities.get(lowestSimilarityPerson)){
                    mostSimilarPersons.remove(lowestSimilarityPerson);
                    mostSimilarPersons.add(currentPerson);
                }
            }
        }
        
        return mostSimilarPersons;
    }
    
    
    private PersonDTO getSmallestSimilarityPerson(List<PersonDTO> mostSimilarPersons, Map<PersonDTO, Double> personSimilarities){
        PersonDTO lowestPerson = null;
        for(PersonDTO person : mostSimilarPersons){
            if(lowestPerson == null){
                lowestPerson = person;
            }else{
                if(personSimilarities.get(person) < personSimilarities.get(lowestPerson)){
                    lowestPerson = person;
                }
            }
        }
        return lowestPerson;
    }
        
}
