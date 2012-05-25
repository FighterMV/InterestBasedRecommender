/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database;

/**
 *
 * @author Marco
 */
public class Interest {
    
    private String name;

    public Interest(String name) {
	this.name = name;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
