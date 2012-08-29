/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.service.PersonService;
import com.rwth.recommender.interestbased.service.recommendation.SimilarityService;
import com.rwth.recommender.interestbased.service.recommendation.component.FreebaseService;
import com.rwth.recommender.interestbased.service.recommendation.component.SimilarPersonFinder;
import com.rwth.recommender.interestbased.service.recommendation.component.WordnetService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SimilarityServiceImpl implements SimilarityService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SimilarityServiceImpl.class);
    
    @Autowired
    PersonService personService;
    
    @Autowired
    SimilarPersonFinder similarPersonFinder;
    
    @Autowired
    WordnetService wordnetService;
    
    @Autowired
    FreebaseService freebaseService;
    
    @Override
    public List<PersonInterestDTO> findSimilarInterests(PersonInterestDTO personInterest) {
	
	LOGGER.debug("Request to find similar keywords for: " + personInterest.getInterest().getName() + " arrived.");
	
	List<PersonInterestDTO> similarInterests = new ArrayList<PersonInterestDTO>();
	similarInterests.addAll(freebaseService.getSimilarInterests(personInterest));
	LOGGER.debug("Added similar interests with freebase");
	
	for(PersonInterestDTO personInterestDTO : similarInterests){
	    personInterestDTO.setPerson(personInterest.getPerson());
	}
	
	LOGGER.debug("Returning " + similarInterests.size() + " similar keywords");
	
	return similarInterests;
	
    }

    @Override
    public List<PersonDTO> findSimilarPersons(PersonDTO user) {
	LOGGER.trace("Starting to find similar persons");
	LOGGER.trace("Starting to get existing users");
	List<PersonDTO> existingUsers = personService.getList();
	int indexOfCurrentUser = -1;
	for(int i = 0; i < existingUsers.size(); i++){
	    PersonDTO existingUser = existingUsers.get(i);
	    if(existingUser.getId().equals(user.getId())){
		indexOfCurrentUser = i;
	    }
	}
	if(indexOfCurrentUser != -1){
	    existingUsers.remove(indexOfCurrentUser);
	}
	List<PersonDTO> similarUsers = similarPersonFinder.getXSimilarPersons(user, existingUsers, Constants.NUMBER_OF_BEST_OBJECTS_TO_RETURN);
	return similarUsers;
    }
    
    @Override
    public List<PersonInterestDTO> getSimilarInterests(List<PersonInterestDTO> personInterests){
	List<PersonInterestDTO> personInterestDTOs = new ArrayList<PersonInterestDTO>();
	for(PersonInterestDTO personInterest : personInterests){
	    personInterestDTOs.addAll(findSimilarInterests(personInterest));
	}
	return personInterestDTOs;
    }
    
}
