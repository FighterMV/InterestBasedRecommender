/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marco
 */
public class PersonDTO {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDTO.class);
    
    private Long id;
    private String name;
    private List<String> personInterestKeywords;
    private List<String> personMainTopicKeywords;
    private List<ItemDTO> providedItems;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<String> getPersonInterestKeywords() {
	return personInterestKeywords;
    }

    public void setPersonInterestKeywords(List<String> personInterestKeywords) {
	this.personInterestKeywords = personInterestKeywords;
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

    public List<String> getPersonMainTopicKeywords() {
	return personMainTopicKeywords;
    }

    public void setPersonMainTopicKeywords(List<String> personMainTopicKeywords) {
	this.personMainTopicKeywords = personMainTopicKeywords;
    }
    
        
}
