/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.dto;

/**
 *
 * @author Marco
 */
public class UserDTO {
    
    private String name;
    private Integer age;
    private GenderDTO gender;
    private String job;

    public UserDTO(String name, Integer age, GenderDTO gender, String job) {
	this.name = name;
	this.age = age;
	this.gender = gender;
	this.job = job;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public GenderDTO getGender() {
	return gender;
    }

    public void setGender(GenderDTO gender) {
	this.gender = gender;
    }

    public String getJob() {
	return job;
    }

    public void setJob(String job) {
	this.job = job;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
        
}
