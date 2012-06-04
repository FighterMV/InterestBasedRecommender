/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.Set;

/**
 *
 * @author Marco
 */
public class RecommendationDTO {
    
    private PersonDTO person;
    
    private Set<ItemRecommendationDTO> itemRecommendations;

    public Set<ItemRecommendationDTO> getItemRecommendations() {
	return itemRecommendations;
    }

    public void setItemRecommendations(Set<ItemRecommendationDTO> itemRecommendations) {
	this.itemRecommendations = itemRecommendations;
    }

    public PersonDTO getPerson() {
	return person;
    }

    public void setPerson(PersonDTO person) {
	this.person = person;
    }
}
