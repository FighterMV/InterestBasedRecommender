/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Marco
 */
@Entity
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @ElementCollection
    private Set<String> personInterestKeywords;
    
    @OneToMany
    private List<ItemRecommendation> itemRecommendations;
    
    public Person(){
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<String> getPersonInterestKeywords() {
	return personInterestKeywords;
    }

    public void setPersonInterestKeywords(Set<String> personInterestKeywords) {
	this.personInterestKeywords = personInterestKeywords;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public List<ItemRecommendation> getItemRecommendations() {
	return itemRecommendations;
    }

    public void setItemRecommendations(List<ItemRecommendation> items) {
	this.itemRecommendations = items;
    }
                        
}
