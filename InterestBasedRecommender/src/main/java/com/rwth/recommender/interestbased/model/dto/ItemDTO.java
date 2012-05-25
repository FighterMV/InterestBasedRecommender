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
    private List<String> keyWords;
    private String link;

    public ItemDTO(String name, List<String> keyWords, String link) {
	this.name = name;
	this.keyWords = keyWords;
	this.link = link;
    }

    public List<String> getKeyWords() {
	return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
	this.keyWords = keyWords;
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
}
