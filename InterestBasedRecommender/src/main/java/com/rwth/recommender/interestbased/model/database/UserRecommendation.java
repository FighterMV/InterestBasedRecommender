/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Marco
 */
@Entity
public class UserRecommendation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private User user;
    
    
    @OneToMany
    @ElementCollection
    private List<ItemRecommendation> recommendedItems;

    public List<ItemRecommendation> getRecommendedItems() {
	return recommendedItems;
    }

    public void setRecommendedItems(List<ItemRecommendation> recommendedItem) {
	this.recommendedItems = recommendedItem;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
        
}
