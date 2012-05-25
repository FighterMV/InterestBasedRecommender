/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

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
    private List<String> userInterestKeywords;
    private Map<Interest, Integer> weightedInterests;    
    
    public User(String name, Date lastRecommendationDate, Map<Interest, Integer> weightedInterests, List<String> userInterestKeywords){
	this.name = name;
	this.lastRecommendationDate = lastRecommendationDate;
	this.weightedInterests = weightedInterests;
	this.userInterestKeywords = userInterestKeywords;

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

    public Map<Interest, Integer> getWeightedInterests() {
	return weightedInterests;
    }

    public void setWeightedInterests(Map<Interest, Integer> weightedInterests) {
	this.weightedInterests = weightedInterests;
    }

    public List<String> getUserInterestKeywords() {
	return userInterestKeywords;
    }

    public void setUserInterestKeywords(List<String> userInterestKeywords) {
	this.userInterestKeywords = userInterestKeywords;
    }
                
}
