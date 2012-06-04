/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

/**
 *
 * @author Marco
 */
public class InterestDTO {
    
    private Long id;
    
    private String name;
    
    private PersonDTO person;
    
    private Integer weighting;

    public InterestDTO() {
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public PersonDTO getPerson() {
	return person;
    }

    public void setPerson(PersonDTO person) {
	this.person = person;
    }

    public Integer getWeighting() {
	return weighting;
    }

    public void setWeighting(Integer weighting) {
	this.weighting = weighting;
    }
        
}
