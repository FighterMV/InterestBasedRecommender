/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper.impl;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.SimpleSimilarPersonFinder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SimpleSimilarPersonFinderImpl implements SimpleSimilarPersonFinder{

    @Autowired
    PersonInterestService personInterestService;
    
    @Override
    public List<PersonDTO> findXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn) {
	Map<PersonDTO, Integer> candidateScoreMap = new HashMap<PersonDTO, Integer>();
	
	for(PersonDTO candidate : persons){
	    int score = getScore(person, candidate);
	    if(score > 0){
		candidateScoreMap.put(candidate, score);
	    }
	}
	
	return getXPersonsWithHighestScore(candidateScoreMap, numberOfPersonsToReturn);
    }
    
    
    private int getScore(PersonDTO person, PersonDTO candidate){
	int score = 0;
	
	List<PersonInterestDTO> personInterests = personInterestService.getPersonInterests(person);
	List<String> keywords = new ArrayList<String>();
	for(PersonInterestDTO personInterestDTO : personInterests){
	    keywords.add(personInterestDTO.getInterest().getName());
	}
	
	List<PersonInterestDTO> candidateInterests = personInterestService.getPersonInterests(candidate);
	List<String> candidateKeywords = new ArrayList<String>();
	for(PersonInterestDTO candidateInterestDTO : candidateInterests){
	    candidateKeywords.add(candidateInterestDTO.getInterest().getName());
	}
	
	for(String keyword : keywords){
	    for(String candidateKeyword : candidateKeywords){
		if(keyword.equals(candidateKeyword)){
		    score++;
		}
	    }
	}
	return score;
    }
    
    
    private List<PersonDTO> getXPersonsWithHighestScore(Map<PersonDTO, Integer> candidateScoreMap, int numberOfPersonsToReturn){
	List<PersonDTO> bestPersons = new ArrayList<PersonDTO>();
	
	for(PersonDTO candidate : candidateScoreMap.keySet()){
	    if(bestPersons.size() <= numberOfPersonsToReturn){
		bestPersons.add(candidate);
	    }else{
		PersonDTO weakestPersonInList = getWeakestPersonInList(bestPersons, candidateScoreMap);
		int candidateScore = candidateScoreMap.get(candidate);
		int weakestPersonInListScore = candidateScoreMap.get(weakestPersonInList);
		if(candidateScore > weakestPersonInListScore){
		    bestPersons.remove(weakestPersonInList);
		    bestPersons.add(candidate);
		}
	    }
	}
	
	return bestPersons;
    }
    
    private PersonDTO getWeakestPersonInList(List<PersonDTO> persons, Map<PersonDTO, Integer> candidatesScoreMap){
	if(persons.isEmpty()){
	    return null;
	}
	PersonDTO weakestPerson = persons.get(0);
	
	for(PersonDTO person : persons){
	    if(candidatesScoreMap.get(person) < candidatesScoreMap.get(weakestPerson)){
		weakestPerson = person;
	    }
	}
	
	return weakestPerson;
	
    }
    
}