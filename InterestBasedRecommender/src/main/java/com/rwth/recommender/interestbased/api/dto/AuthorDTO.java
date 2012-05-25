/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api.dto;

/**
 *
 * @author Marco
 */
public class AuthorDTO {
    
    private String name;

    public AuthorDTO(String name){
	this.name = name;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
        
}
