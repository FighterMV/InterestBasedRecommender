/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marco
 */
public class UserDTO {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDTO.class);
    
    private Long id;
    private String name;
    private Date lastRecommendationDate;
    private Set<String> userInterestKeywords;
    private Map<InterestDTO, Integer> weightedInterests;    
        
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

    public Map<InterestDTO, Integer> getWeightedInterests() {
	return weightedInterests;
    }

    public void setWeightedInterests(Map<InterestDTO, Integer> weightedInterests) {
	this.weightedInterests = weightedInterests;
    }

    public Set<String> getUserInterestKeywords() {
	return userInterestKeywords;
    }

    public void setUserInterestKeywords(Set<String> userInterestKeywords) {
	this.userInterestKeywords = userInterestKeywords;
    }
    
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
        
}
