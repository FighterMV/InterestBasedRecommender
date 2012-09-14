/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.List;

/**
 *
 * @author Marco
 */
public class RecommendationDTO {
    
    private PersonDTO person;
    
    private List<ItemRecommendationDTO> itemRecommendations;
    
    private List<ItemRecommendationDTO> itemsBySimilarUsers;
    
    List<PersonDTO> similarUsers;

    public List<ItemRecommendationDTO> getItemRecommendations() {
	return itemRecommendations;
    }

    public void setItemRecommendations(List<ItemRecommendationDTO> itemRecommendations) {
	this.itemRecommendations = itemRecommendations;
    }

    public PersonDTO getPerson() {
	return person;
    }

    public void setPerson(PersonDTO person) {
	this.person = person;
    }

    public List<ItemRecommendationDTO> getItemsBySimilarUsers() {
	return itemsBySimilarUsers;
    }

    public void setItemsBySimilarUsers(List<ItemRecommendationDTO> itemsBySimilarUsers) {
	this.itemsBySimilarUsers = itemsBySimilarUsers;
    }

    public List<PersonDTO> getSimilarUsers() {
	return similarUsers;
    }

    public void setSimilarUsers(List<PersonDTO> similarUsers) {
	this.similarUsers = similarUsers;
    }
    
    
}
