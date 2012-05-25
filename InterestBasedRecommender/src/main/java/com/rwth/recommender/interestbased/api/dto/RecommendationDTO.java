/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api.dto;

import java.util.List;

/**
 *
 * @author Marco
 */
public class RecommendationDTO {
    
    private UserDTO user;
    private List<RecommendedItemDTO> recommendedItems;
    private List<RecommendedInterestDTO> recommendedInterests;

    public RecommendationDTO(UserDTO user, List<RecommendedItemDTO> recommendedItems, List<RecommendedInterestDTO> recommendedInterests) {
	this.user = user;
	this.recommendedItems = recommendedItems;
	this.recommendedInterests = recommendedInterests;
    }

    public List<RecommendedInterestDTO> getRecommendedInterests() {
	return recommendedInterests;
    }

    public void setRecommendedInterests(List<RecommendedInterestDTO> recommendedInterests) {
	this.recommendedInterests = recommendedInterests;
    }

    public List<RecommendedItemDTO> getRecommendedItems() {
	return recommendedItems;
    }

    public void setRecommendedItems(List<RecommendedItemDTO> recommendedItems) {
	this.recommendedItems = recommendedItems;
    }

    public UserDTO getUser() {
	return user;
    }

    public void setUser(UserDTO user) {
	this.user = user;
    }
        
}
