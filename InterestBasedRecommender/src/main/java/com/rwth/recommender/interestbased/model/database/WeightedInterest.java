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
public class WeightedInterest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Interest interest;
    
    private Integer weighting;

    public Interest getInterest() {
	return interest;
    }

    public Integer getWeighting() {
	return weighting;
    }

    public void setInterest(Interest interest) {
	this.interest = interest;
    }

    public void setWeighting(Integer weighting) {
	this.weighting = weighting;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
           
}
