/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.model.assembler.ItemRecommendationAssembler;
import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.database.ItemRecommendation;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.RecommendationDTO;
import com.rwth.recommender.interestbased.model.service.InterestService;
import com.rwth.recommender.interestbased.model.service.ItemRecommendationService;
import com.rwth.recommender.interestbased.model.service.PersonService;
import com.rwth.recommender.interestbased.recommendation.service.RecommendationService;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
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
public class RecommendationServiceImpl implements RecommendationService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    @Autowired
    SimilarityService similarityService;
    
    @Autowired
    PersonAssembler userAssembler;
   
    @Autowired
    PersonService personService;
    
    @Autowired
    InterestService interestService;
    
    @Autowired
    ItemRecommendationService itemRecommendationService;
    
    @Override
    public RecommendationDTO getRecommendations(PersonDTO personDTO, List<InterestDTO> interestDTOs) {

	LOGGER.debug("Storing a new user with name " + personDTO.getName() + " in the database");
	personService.storeInDatabase(personDTO);
	interestService.storeInDatabase(interestDTOs);
	
	LOGGER.trace("Starting to search similar users for user " + personDTO.getName());
	List<PersonDTO> similarUsers = similarityService.findSimilarPersons(personDTO);
	LOGGER.trace("Found " + similarUsers.size() + " similar users for user " + personDTO.getName());
	
	RecommendationDTO recommendation = new RecommendationDTO();
	recommendation.setPerson(personDTO);
	
	List<ItemRecommendationDTO> itemsToBeRecommended = new ArrayList<ItemRecommendationDTO>();
	for(PersonDTO similarUser : similarUsers){
	    //!CALCULATE ACCURACY OF ITEMS AND DONT ADD JUST ALL ITEMS
	    for(ItemRecommendationDTO itemRecommendation : similarUser.getItemRecommendations()){
		if(!containsLink(itemsToBeRecommended, itemRecommendation)){
		    itemsToBeRecommended.add(itemRecommendation);
		}
	    }
	}
	
	recommendation.setItemRecommendations(itemsToBeRecommended);
	
	LOGGER.debug("Storing the recommendation for user " + personDTO.getName() + " in the database");
	personService.updatePersonInDatabase(personDTO);
	
	return recommendation;
    }
    
    private Boolean containsLink(List<ItemRecommendationDTO> itemRecommendations, ItemRecommendationDTO itemRecommendation){
	for(ItemRecommendationDTO itemRecommendationDTO: itemRecommendations){
	    if(itemRecommendationDTO.getItem().getLink().equals(itemRecommendation.getItem().getLink())){
		return true;
	    }
	}
	return false;
    }
    
}
