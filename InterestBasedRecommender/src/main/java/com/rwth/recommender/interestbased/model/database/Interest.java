/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

import javax.persistence.*;

/**
 *
 * @author Marco
 */
@Entity
public class Interest {    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private Integer weighting;
    
    @ManyToOne
    private Person person;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
        
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    public Integer getWeighting() {
	return weighting;
    }

    public void setWeighting(Integer weighting) {
	this.weighting = weighting;
    }   
    
}
