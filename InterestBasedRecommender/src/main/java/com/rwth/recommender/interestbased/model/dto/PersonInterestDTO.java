/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

/**
 *
 * @author Marco
 */
public class PersonInterestDTO {
    
    private Long id;
    
    private PersonDTO person;
    
    private InterestDTO interest;
    
    private int weighting;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

        
    public InterestDTO getInterest() {
	return interest;
    }

    public void setInterest(InterestDTO interest) {
	this.interest = interest;
    }

    public PersonDTO getPerson() {
	return person;
    }

    public void setPerson(PersonDTO person) {
	this.person = person;
    }

    public int getWeighting() {
	return weighting;
    }

    public void setWeighting(int weighting) {
	this.weighting = weighting;
    }
    
    
    
}
