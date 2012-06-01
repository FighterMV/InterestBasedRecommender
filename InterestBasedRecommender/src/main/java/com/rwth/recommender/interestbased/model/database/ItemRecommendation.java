/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Marco
 */
@Entity
public class ItemRecommendation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Item item;
    
    private Integer accuracy;

    public Item getItem() {
	return item;
    }

    public Integer getAccuracy() {
	return accuracy;
    }

    public void setItem(Item item) {
	this.item = item;
    }

    public void setAccuracy(Integer score) {
	this.accuracy = score;
    }
    
    
    
}
