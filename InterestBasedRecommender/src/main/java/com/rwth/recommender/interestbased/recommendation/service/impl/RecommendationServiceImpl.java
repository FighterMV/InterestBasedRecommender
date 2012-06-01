/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;
import com.rwth.recommender.interestbased.model.service.UserRecommendationService;
import com.rwth.recommender.interestbased.recommendation.service.RecommendationService;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
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
    UserRecommendationService userRecommendationService;
    
    @Override
    public UserRecommendationDTO getRecommendations(UserDTO user) {

	LOGGER.trace("Starting to search similar users for user " + user.getName());
	List<UserDTO> similarUsers = similarityService.findSimilarUsers(user);
	LOGGER.trace("Found " + similarUsers.size() + " similar users for user " + user.getName());
	
	List<ItemRecommendationDTO> itemsToBeRecommended = new ArrayList<ItemRecommendationDTO>();
	for(UserDTO similarUser : similarUsers){
	    //!CALCULATE ACCURACY OF ITEMS AND DONT ADD JUST ALL ITEMS
	    UserRecommendationDTO userRecommendation = userRecommendationService.getRecommendationForUser(similarUser.getId());
	    itemsToBeRecommended.addAll(userRecommendation.getRecommendedItems());
	}
	
	UserRecommendationDTO recommendation = new UserRecommendationDTO();
	recommendation.setUser(user);
	recommendation.setRecommendedItems(itemsToBeRecommended);
	
	
	return recommendation;
    }
    
}
