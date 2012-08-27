/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marco
 */
public class PersonDTO {
        
    private Long id;
    private String name;
    private List<ItemDTO> providedItems;
    private List<String> personMainTopics;
    private List<PersonInterestDTO> personInterests;

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

    public List<ItemDTO> getProvidedItems() {
	return providedItems;
    }

    public void setProvidedItems(List<ItemDTO> providedItems) {
	this.providedItems = providedItems;
    }

    public List<String> getPersonMainTopics() {
	return personMainTopics;
    }

    public void setPersonMainTopics(List<String> personMainTopics) {
	this.personMainTopics = personMainTopics;
    }

    public List<PersonInterestDTO> getPersonInterests() {
	return personInterests;
    }

    public void setPersonInterests(List<PersonInterestDTO> personInterests) {
	this.personInterests = personInterests;
    }
    
    /**
     * 
     * @return a List of Strings which represents the interest keywords without weighting 
     */
    public List<String> getInterestKeywords(){
	List<String> interestKeywords = new ArrayList<String>();
	for(PersonInterestDTO personInterest : personInterests){
	    interestKeywords.add(personInterest.getInterest().getName());
	}
	return interestKeywords;
    }
        
}
