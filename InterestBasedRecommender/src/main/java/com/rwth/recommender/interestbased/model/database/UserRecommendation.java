/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import java.util.List;

/**
 *
 * @author Marco
 */
public class UserRecommendation {
    
    private User user;
    private List<Item> recommendedItem;

    public List<Item> getRecommendedItem() {
	return recommendedItem;
    }

    public void setRecommendedItem(List<Item> recommendedItem) {
	this.recommendedItem = recommendedItem;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }
    
}
