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
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @ElementCollection
    private List<String> describingKeywords;
    
    private String link;

    public Item() {
    }

    public List<String> getDescribingKeywords() {
	return describingKeywords;
    }

    public void setDescribingKeywords(List<String> keyWords) {
	this.describingKeywords = keyWords;
    }

    public String getLink() {
	return link;
    }

    public void setLink(String link) {
	this.link = link;
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
               
}
