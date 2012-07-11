/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.service.PersonService;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import com.rwth.recommender.interestbased.recommendation.service.component.FreebaseService;
import com.rwth.recommender.interestbased.recommendation.service.component.SimilarItemFinder;
import com.rwth.recommender.interestbased.recommendation.service.component.SimilarPersonFinder;
import com.rwth.recommender.interestbased.recommendation.service.component.WordnetService;
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
    public List<String> findSimilarKeywords(String keyword) {
	
	Set<String> similarKeywords = new HashSet<String>();
	similarKeywords.addAll(wordnetService.findSimilarKeywords(keyword));
	similarKeywords.addAll(freebaseService.getSimilarKeywords(keyword));
	
	return new ArrayList<String>(similarKeywords);
	
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
    public List<String> getInterestKeywords(List<InterestDTO> weightedInterests){
	List<String> userInterestKeywords = new ArrayList<String>();
	for(InterestDTO interest : weightedInterests){
	    userInterestKeywords.add(interest.getName());
	    if(interest.getWeighting() > Constants.MINIMUM_VALUE_TO_BE_GOOD_INTEREST){
		List<String> similarKeywords = findSimilarKeywords(interest.getName());
		userInterestKeywords.addAll(similarKeywords);
	    }
	}
	return userInterestKeywords;
    }

    @Override
    public List<ItemDTO> findSimilarItems(List<ItemDTO> items, PersonDTO person) {
	List<ItemDTO> similarItems = similarItemFinder.getXSimilarItems(items, person, Constants.NUMBER_OF_BEST_OBJECTS_TO_RETURN);
	return similarItems;
    }
    
}
