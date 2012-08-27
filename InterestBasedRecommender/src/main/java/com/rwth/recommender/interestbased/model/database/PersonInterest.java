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
public class PersonInterest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Person person;
    
    @OneToOne
    private Interest interest;
    
    private int weighting;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Interest getInterest() {
	return interest;
    }

    public void setInterest(Interest interest) {
	this.interest = interest;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    public int getWeighting() {
	return weighting;
    }

    public void setWeighting(int weighting) {
	this.weighting = weighting;
    }
        
}
