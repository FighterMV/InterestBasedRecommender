/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Marco
 */
public class UserDTO {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDTO.class);
        
    @Autowired
    SimilarityService similarityService;
    
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
	setInterestKeywords();
    }

    public Set<String> getUserInterestKeywords() {
	return userInterestKeywords;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
        
    private void setInterestKeywords(){
	LOGGER.trace("Weighted Interests for user " + this.name + " were changed. Calculating a new list of userInterestKeywords");
	userInterestKeywords = new HashSet<String>();
	for(InterestDTO interest : weightedInterests.keySet()){
	    if(weightedInterests.get(interest) > Constants.MINIMUM_VALUE_TO_BE_GOOD_INTEREST){
		userInterestKeywords.addAll(similarityService.findSimilarKeywords(interest.getName()));
	    }
	}
    }
    
}