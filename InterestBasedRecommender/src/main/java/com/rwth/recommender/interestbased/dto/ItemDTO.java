/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.dto;

import java.util.Date;

/**
 *
 * @author Marco
 */
public class ItemDTO {
    
    private String name;
    private Date publishDate;
    private AuthorDTO author;

    public ItemDTO(String name, Date publishDate, AuthorDTO author) {
	this.name = name;
	this.publishDate = publishDate;
	this.author = author;
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
        
}
