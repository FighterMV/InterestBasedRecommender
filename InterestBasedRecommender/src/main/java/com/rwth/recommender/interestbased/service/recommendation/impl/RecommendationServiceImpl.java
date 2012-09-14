/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.dto.*;
import com.rwth.recommender.interestbased.model.service.InterestService;
import com.rwth.recommender.interestbased.model.service.ItemService;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import com.rwth.recommender.interestbased.model.service.PersonService;
import com.rwth.recommender.interestbased.service.recommendation.RecommendationService;
import com.rwth.recommender.interestbased.service.recommendation.SimilarityService;
import com.rwth.recommender.interestbased.service.recommendation.component.FreebaseService;
import com.rwth.recommender.interestbased.service.recommendation.component.UserItemProvider;
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
    
    @Autowired
    UserItemProvider userItemProvider;
    
    @Autowired
    ItemService itemService;
    
    @Override
    public RecommendationDTO getRecommendations(PersonDTO personDTO, List<PersonInterestDTO> personInterests) {
	
	LOGGER.debug("A new request for a Recommendation for a person with name: " + personDTO.getName() + " arrived");
	
	LOGGER.debug("Storing person with name: " + personDTO.getName() + " and his interests in the database");
	List<String> personKeywords = new ArrayList<String>();
	for(PersonInterestDTO personInterestDTO : personInterests){
	    personKeywords.add(personInterestDTO.getInterest().getName());
	}
	personDTO.setPersonMainTopics(freebaseService.getMainTopics(personKeywords));
	
	itemService.storeItemsInDatabase(personDTO.getProvidedItems());
	
	List<Long> providedItemIds = new ArrayList<Long>();
	for(ItemDTO itemDTO : personDTO.getProvidedItems()){
	    providedItemIds.add(itemDTO.getId());
	}
	
	personDTO.setProvidedItems(new ArrayList<ItemDTO>());
	
	personService.storeInDatabase(personDTO);
	
	personService.updatePersonInDatabase(personDTO, providedItemIds);
	
	normWeightings(personInterests);
	personInterestService.storeInDatabase(personInterests);
	
	personInterestService.findAndStoreSimilarInterests(personDTO);
					
	LOGGER.debug("Starting to search similar users for user " + personDTO.getName());
	List<PersonDTO> similarUsers = similarityService.findSimilarPersons(personDTO);
	LOGGER.debug("Found " + similarUsers.size() + " similar users for user " + personDTO.getName());
	
	RecommendationDTO recommendation = new RecommendationDTO();
	recommendation.setPerson(personDTO);
	recommendation.setSimilarUsers(similarUsers);
	
	LOGGER.debug("Adding recommended items to the result");
	List<ItemRecommendationDTO> itemsToBeRecommended = new ArrayList<ItemRecommendationDTO>();
	List<ItemRecommendationDTO> itemsBySimilarUsers = new ArrayList<ItemRecommendationDTO>();
	for(PersonDTO similarUser : similarUsers){
	    LOGGER.debug("Searching for similar items");
	    UserMappingItemsDTO matchingItemsForUser = userItemProvider.orderItemsFittingForUser(personDTO, similarUser);
	    for(ItemDTO similarUserItem : matchingItemsForUser.getMappingItems()){
		if(!containsLink(itemsToBeRecommended, similarUserItem) &&!isOwnProvidedItem(personDTO, similarUserItem)){
		    ItemRecommendationDTO itemRecommendation = new ItemRecommendationDTO();
		    itemRecommendation.setItem(similarUserItem);
		    itemsToBeRecommended.add(itemRecommendation);
		}
	    }
	    
	    for(ItemDTO similarUserItem : matchingItemsForUser.getOtherItems()){
		if(!containsLink(itemsBySimilarUsers, similarUserItem) &&!isOwnProvidedItem(personDTO, similarUserItem)){
		    ItemRecommendationDTO itemRecommendation = new ItemRecommendationDTO();
		    itemRecommendation.setItem(similarUserItem);
		    itemsBySimilarUsers.add(itemRecommendation);
		}
	    }
	}
	
	recommendation.setItemRecommendations(itemsToBeRecommended);
	recommendation.setItemsBySimilarUsers(itemsBySimilarUsers);
	
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
    
    
    private void normWeightings(List<PersonInterestDTO> personInterestDTOs){
	int maxRating = 1;
	
	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    maxRating = Math.max(maxRating, personInterestDTO.getWeighting());
	}
	
	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    int oldRating = personInterestDTO.getWeighting();
	    double newRating = (new Double(oldRating) / new Double(maxRating)) * Constants.MAX_WEIGHTING;
	    personInterestDTO.setWeighting((int)newRating);
	}
    }
    
}
