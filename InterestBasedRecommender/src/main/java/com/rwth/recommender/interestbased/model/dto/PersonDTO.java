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
    private String link;
    private List<ItemDTO> providedItems;
    private List<String> personMainTopics;

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

    public String getLink() {
	return link;
    }

    public void setLink(String link) {
	this.link = link;
    }
    
    
        
}
