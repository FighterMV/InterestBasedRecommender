/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.dto;

import java.util.List;

/**
 *
 * @author Marco
 */
public class RecommendationDTO {
    
    private UserDTO user;
    private List<ItemDTO> recommendedItems;
    private List<InterestDTO> recommendedInterests;

    public RecommendationDTO(UserDTO user, List<ItemDTO> recommendedItems, List<InterestDTO> recommendedInterests) {
	this.user = user;
	this.recommendedItems = recommendedItems;
	this.recommendedInterests = recommendedInterests;
    }

    public List<InterestDTO> getRecommendedInterests() {
	return recommendedInterests;
    }

    public void setRecommendedInterests(List<InterestDTO> recommendedInterests) {
	this.recommendedInterests = recommendedInterests;
    }

    public List<ItemDTO> getRecommendedItems() {
	return recommendedItems;
    }

    public void setRecommendedItems(List<ItemDTO> recommendedItems) {
	this.recommendedItems = recommendedItems;
    }

    public UserDTO getUser() {
	return user;
    }

    public void setUser(UserDTO user) {
	this.user = user;
    }
        
}
