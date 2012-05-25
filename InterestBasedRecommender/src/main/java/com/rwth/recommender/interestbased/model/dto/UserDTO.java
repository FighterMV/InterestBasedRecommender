/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Marco
 */
public class UserDTO {
        
    @Autowired
    SimilarityService similarityService;
    
    private String name;
    private Date lastRecommendationDate;
    private List<String> userInterestKeywords;
    private Map<InterestDTO, Integer> weightedInterests;    
    
    public UserDTO(String name, Date lastRecommendationDate, Map<InterestDTO, Integer> weightedInterests){
	this.name = name;
	this.lastRecommendationDate = lastRecommendationDate;
	this.weightedInterests = weightedInterests;
	setInterestKeywords();
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

    public Map<InterestDTO, Integer> getWeightedInterests() {
	return weightedInterests;
    }

    public void setWeightedInterests(Map<InterestDTO, Integer> weightedInterests) {
	this.weightedInterests = weightedInterests;
    }

    public List<String> getUserInterestKeywords() {
	return userInterestKeywords;
    }
    
    private void setInterestKeywords(){
	userInterestKeywords = new ArrayList<String>();
	for(InterestDTO interest : weightedInterests.keySet()){
	    if(weightedInterests.get(interest) > Constants.MINIMUM_VALUE_TO_BE_GOOD_INTEREST){
		userInterestKeywords.addAll(similarityService.findSimilarKeywords(interest.getName()));
	    }
	}
    }
    
}
