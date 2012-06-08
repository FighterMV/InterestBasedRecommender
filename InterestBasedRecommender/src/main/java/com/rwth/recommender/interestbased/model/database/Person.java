/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import java.util.List;
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
    
    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> personInterestKeywords;
    
    @OneToMany
    private List<Item> providedItems;
    
    public Person(){
    }

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

    public List<Item> getProvidedItems() {
	return providedItems;
    }

    public void setProvidedItems(List<Item> providedItems) {
	this.providedItems = providedItems;
    }

    
                        
}
