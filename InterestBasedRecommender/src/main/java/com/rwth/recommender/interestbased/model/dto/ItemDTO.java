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
public class ItemDTO {
    
    private String name;
    private String link;
    private Long id;
    private List<String> describingKeywords;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLink() {
	return link;
    }

    public String getName() {
	return name;
    }

    public void setLink(String link) {
	this.link = link;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<String> getDescribingKeywords() {
	return describingKeywords;
    }

    public void setDescribingKeywords(List<String> describingKeywords) {
	this.describingKeywords = describingKeywords;
    }
                
}
