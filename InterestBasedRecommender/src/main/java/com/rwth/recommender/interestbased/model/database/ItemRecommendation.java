/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import javax.persistence.*;

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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    
    
}
