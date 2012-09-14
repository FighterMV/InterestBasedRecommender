/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Marco
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @Temporal(TemporalType.DATE)
    private Date lastRecommendationDate;
    
    @ElementCollection
    private Set<String> userInterestKeywords;
    
    @OneToMany
    @ElementCollection
    private List<WeightedInterest> weightedInterests;    
    
    public User(){
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

    public List<WeightedInterest> getWeightedInterests() {
	return weightedInterests;
    }

    public void setWeightedInterests(List<WeightedInterest> weightedInterests) {
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
