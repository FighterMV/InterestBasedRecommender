/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marco
 */
public class UserDTO {
    private String name;
    private Date lastRecommendationDate;
    private Map<Long, Integer> weightedItems;    
    private List<ItemDTO> recommendedItems;

    public Date getLastRecommendationDate() {
	return lastRecommendationDate;
    }

    public void setLastRecommendationDate(Date lastRecommendationDate) {
	this.lastRecommendationDate = lastRecommendationDate;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<ItemDTO> getRecommendedItems() {
	return recommendedItems;
    }

    public void setRecommendedItems(List<ItemDTO> recommendedItems) {
	this.recommendedItems = recommendedItems;
    }

    public Map<Long, Integer> getWeightedItems() {
	return weightedItems;
    }

    public void setWeightedItems(Map<Long, Integer> weightedItems) {
	this.weightedItems = weightedItems;
    }
}
