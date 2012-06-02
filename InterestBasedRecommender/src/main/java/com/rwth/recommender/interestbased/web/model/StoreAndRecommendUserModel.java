/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.web.model;

/**
 *
 * @author Marco
 */
public class StoreAndRecommendUserModel {
    
    private String username;
    private String interests;
    private String weightings;

    public StoreAndRecommendUserModel(){
	username = "";
	interests = "";
	weightings = "";
    }
    
    public String getInterests() {
	return interests;
    }

    public void setInterests(String interests) {
	this.interests = interests;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getWeightings() {
	return weightings;
    }

    public void setWeightings(String weightings) {
	this.weightings = weightings;
    }
        
    
}
