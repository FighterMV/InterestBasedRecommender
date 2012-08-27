/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.service.PersonService;
import com.rwth.recommender.interestbased.service.recommendation.SimilarityService;
import com.rwth.recommender.interestbased.service.recommendation.component.FreebaseService;
import com.rwth.recommender.interestbased.service.recommendation.component.SimilarItemFinder;
import com.rwth.recommender.interestbased.service.recommendation.component.SimilarPersonFinder;
import com.rwth.recommender.interestbased.service.recommendation.component.WordnetService;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.*;
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
    SimilarItemFinder similarItemFinder;
    
    @Autowired
    WordnetService wordnetService;
    
    @Autowired
    FreebaseService freebaseService;
    
    @Override
    public List<PersonInterestDTO> findSimilarInterests(PersonInterestDTO personInterest) {
	
	LOGGER.debug("Request to find similar keywords for: " + personInterest.getInterest().getName() + " arrived.");
	
	Set<String> similarKeywords = new HashSet<String>();
	similarKeywords.addAll(wordnetService.findSimilarKeywords(personInterest.getInterest().getName()));
	LOGGER.debug("Added similar keywords with wordnet");
	similarKeywords.addAll(freebaseService.getSimilarKeywords(personInterest.getInterest().getName()));
	LOGGER.debug("Added similar keywords with freebase");
	
	LOGGER.debug("Returning " + similarKeywords.size() + " similar keywords");
	
	List<PersonInterestDTO> personInterestDTOs = new ArrayList<PersonInterestDTO>();
	for(String similarKeyword : similarKeywords){
	    PersonInterestDTO personInterestDTO = new PersonInterestDTO();
	    personInterestDTO.setPerson(personInterest.getPerson());
	    InterestDTO interestDTO = new InterestDTO();
	    interestDTO.setName(similarKeyword);
	    personInterestDTO.setInterest(interestDTO);
	    //!TODO CALCULATE AND SET WEIGHTING
	}
	
	return personInterestDTOs;
	
    }

    @Override
    public List<PersonDTO> findSimilarPersons(PersonDTO user) {
	LOGGER.trace("Starting to find similar persons");
	LOGGER.trace("Starting to get existing users");
	List<PersonDTO> existingUsers = personService.getList();
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

    @Override
    public List<ItemDTO> findSimilarItems(List<ItemDTO> items, PersonDTO person) {
	List<ItemDTO> similarItems = similarItemFinder.getXSimilarItems(items, person, Constants.NUMBER_OF_BEST_OBJECTS_TO_RETURN);
	return similarItems;
    }
    
}