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
    
    @OneToMany
    private List<Item> providedItems;
    
    @ElementCollection
    private List<String> personMainTopics;
    
    @ElementCollection
    private List<PersonInterest> personInterests;
    
    public Person(){
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

    public List<Item> getProvidedItems() {
	return providedItems;
    }

    public void setProvidedItems(List<Item> providedItems) {
	this.providedItems = providedItems;
    }

    public List<String> getPersonMainTopics() {
	return personMainTopics;
    }

    public void setPersonMainTopics(List<String> personMainTopics) {
	this.personMainTopics = personMainTopics;
    }

    public List<PersonInterest> getPersonInterests() {
	return personInterests;
    }

    public void setPersonInterests(List<PersonInterest> personInterests) {
	this.personInterests = personInterests;
    }
    
    
                        
}
