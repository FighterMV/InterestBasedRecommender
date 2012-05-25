/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.List;

/**
 *
 * @author Marco
 */
public class UserRecommendationDTO {
    
    private UserDTO user;
    private List<ItemDTO> recommendedItems;

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
