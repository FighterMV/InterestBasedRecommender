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
    private String itemNames;
    private String itemLinks;

    public StoreAndRecommendUserModel(){
	username = "";
	interests = "";
	weightings = "";
	itemNames = "";
	itemLinks = "";
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

    public String getItemLinks() {
	return itemLinks;
    }

    public void setItemLinks(String itemLinks) {
	this.itemLinks = itemLinks;
    }

    public String getItemNames() {
	return itemNames;
    }

    public void setItemNames(String itemNames) {
	this.itemNames = itemNames;
    }
        
    
}
