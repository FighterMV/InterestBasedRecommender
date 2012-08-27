/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.impl;

import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.dto.*;
import com.rwth.recommender.interestbased.model.service.InterestService;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import com.rwth.recommender.interestbased.model.service.PersonService;
import com.rwth.recommender.interestbased.service.recommendation.RecommendationService;
import com.rwth.recommender.interestbased.service.recommendation.SimilarityService;
import com.rwth.recommender.interestbased.service.recommendation.component.FreebaseService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class RecommendationServiceImpl implements RecommendationService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    @Autowired
    SimilarityService similarityService;
    
    @Autowired
    FreebaseService freebaseService;
    
    @Autowired
    PersonAssembler userAssembler;
   
    @Autowired
    PersonService personService;
    
    @Autowired
    PersonInterestService personInterestService;
    
    @Autowired
    InterestService interestService;
    
    @Override
    public RecommendationDTO getRecommendations(PersonDTO personDTO, List<PersonInterestDTO> personInterests) {

	preparePerson(personDTO);
	
	LOGGER.debug("A new request for a Recommendation for a person with name: " + personDTO.getName() + " arrived");
	
	LOGGER.debug("Storing person with name: " + personDTO.getName() + " and his interests in the database");
	personService.storeInDatabase(personDTO);
	
	personInterestService.storeInDatabase(personInterests);
	
	personDTO.setPersonInterests(personInterests);
	personService.updatePersonInDatabase(personDTO, personInterests);
	
	LOGGER.debug("Starting to search similar users for user " + personDTO.getName());
	List<PersonDTO> similarUsers = similarityService.findSimilarPersons(personDTO);
	LOGGER.debug("Found " + similarUsers.size() + " similar users for user " + personDTO.getName());
	
	RecommendationDTO recommendation = new RecommendationDTO();
	recommendation.setPerson(personDTO);
	
	LOGGER.debug("Adding recommended items to the result");
	List<ItemRecommendationDTO> itemsToBeRecommended = new ArrayList<ItemRecommendationDTO>();
	for(PersonDTO similarUser : similarUsers){
	    LOGGER.debug("Searching for similar items");
	    List<ItemDTO> matchingItemsForUser = similarUser.getProvidedItems();
	    for(ItemDTO similarUserItem : matchingItemsForUser){
		if(!containsLink(itemsToBeRecommended, similarUserItem) &&!isOwnProvidedItem(personDTO, similarUserItem)){
		    ItemRecommendationDTO itemRecommendation = new ItemRecommendationDTO();
		    itemRecommendation.setItem(similarUserItem);
		    //!TODO set accuracy
		    itemsToBeRecommended.add(itemRecommendation);
		}
	    }
	}
	
	recommendation.setItemRecommendations(itemsToBeRecommended);
	
	LOGGER.debug("Storing the recommendation for user " + personDTO.getName() + " in the database");
	personService.updatePersonInDatabase(personDTO, personInterests);
	
	return recommendation;
    }
    
    private Boolean containsLink(List<ItemRecommendationDTO> itemRecommendations, ItemDTO recommendedItem){
	for(ItemRecommendationDTO itemRecommendationDTO: itemRecommendations){
	    if(itemRecommendationDTO.getItem().getLink().equals(recommendedItem.getLink())){
		return true;
	    }
	}
	return false;
    }
    
    private Boolean isOwnProvidedItem(PersonDTO person, ItemDTO item){
	for(ItemDTO providedItem : person.getProvidedItems()){
	    if(providedItem.getLink().equals(item.getLink())){
		return true;
	    }
	}
	return false;
    }
    
    private void preparePerson(PersonDTO personDTO){
	
	List<PersonInterestDTO> personInterests = personDTO.getPersonInterests();
	
	List<PersonInterestDTO> similarInterests = similarityService.getSimilarInterests(personInterests);
	personDTO.setPersonInterests(similarInterests);
	
	List<String> interestKeywords = new ArrayList<String>();
	for(PersonInterestDTO personInterestDTO : personInterests){
	    interestKeywords.add(personInterestDTO.getInterest().getName());
	}
	
	List<String> interestMainTopicKeywords = freebaseService.getMainTopics(interestKeywords);
	personDTO.setPersonMainTopics(interestMainTopicKeywords);
	
	normWeightings(personInterests);
    }
    
    
    private void normWeightings(List<PersonInterestDTO> personInterestDTOs){
	int maxRating = 1;
	
	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    maxRating = Math.max(maxRating, personInterestDTO.getWeighting());
	}
	
	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    int oldRating = personInterestDTO.getWeighting();
	    int newRating = (int) ((oldRating/maxRating)*100);
	    personInterestDTO.setWeighting(newRating);
	}
    }
    
}
