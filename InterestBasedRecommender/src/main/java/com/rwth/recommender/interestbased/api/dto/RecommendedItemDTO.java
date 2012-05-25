/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api.dto;

import java.util.Date;

/**
 *
 * @author Marco
 */
public class RecommendedItemDTO {
    
    private String name;
    private Date publishDate;
    private AuthorDTO author;
    private String link;

    public RecommendedItemDTO(String name, Date publishDate, AuthorDTO author, String link) {
	this.name = name;
	this.publishDate = publishDate;
	this.author = author;
	this.link = link;
    }
    
    public AuthorDTO getAuthor() {
	return author;
    }

    public void setAuthor(AuthorDTO author) {
	this.author = author;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getPublishDate() {
	return publishDate;
    }

    public void setPublishDate(Date publishDate) {
	this.publishDate = publishDate;
    }

    public String getLink() {
	return link;
    }
            
}
