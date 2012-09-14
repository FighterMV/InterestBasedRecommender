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
    
    private Long id;
    private UserDTO user;
    private List<ItemRecommendationDTO> recommendedItems;

    public List<ItemRecommendationDTO> getRecommendedItems() {
	return recommendedItems;
    }

    public void setRecommendedItems(List<ItemRecommendationDTO> recommendedItems) {
	this.recommendedItems = recommendedItems;
    }

    public UserDTO getUser() {
	return user;
    }

    public void setUser(UserDTO user) {
	this.user = user;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
            
}
