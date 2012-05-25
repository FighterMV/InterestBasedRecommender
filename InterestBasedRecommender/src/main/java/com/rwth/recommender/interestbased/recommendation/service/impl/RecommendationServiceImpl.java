/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import com.rwth.recommender.interestbased.model.dto.RecommendedItemDTO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;
import com.rwth.recommender.interestbased.model.service.UserRecommendationService;
import com.rwth.recommender.interestbased.recommendation.service.RecommendationService;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Marco
 */
public class RecommendationServiceImpl implements RecommendationService{

    @Autowired
    SimilarityService similarityService;
    
    @Autowired
    UserRecommendationService userRecommendationService;
    
    @Override
    public UserRecommendationDTO getRecommendations(UserDTO user) {
		
	List<UserDTO> similarUsers = similarityService.findSimilarUsers(user);
	
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
