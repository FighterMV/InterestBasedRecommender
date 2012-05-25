/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marco
 */
public class User {
    
    private String name;
    private Date lastRecommendationDate;
    private Map<Long, Integer> weightedItems;    
    private List<Item> recommendedItems;

    public User(String name, Date lastRecommendationDate, List<Item> recommendedItems, Map<Long, Integer> weightedItems) {
	this.name = name;
	this.lastRecommendationDate = lastRecommendationDate;
	this.recommendedItems = recommendedItems;
    }

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

    public List<Item> getRecommendedItems() {
	return recommendedItems;
    }

    public void setRecommendedItems(List<Item> recommendedItems) {
	this.recommendedItems = recommendedItems;
    }

    public Map<Long, Integer> getWeightedItems() {
	return weightedItems;
    }

    public void setWeightedItems(Map<Long, Integer> weightedItems) {
	this.weightedItems = weightedItems;
    }
            
}
