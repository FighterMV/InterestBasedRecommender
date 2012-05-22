/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.dto;

/**
 *
 * @author Marco
 */
public class InterestDTO {
    
    private String name;
    private String source;

    public InterestDTO(String name, String source) {
	this.name = name;
	this.source = source;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }
       
}
