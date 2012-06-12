/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.SVDSimilarityCalculator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marco
 */
public class SVDSimilarityCalculatorImplTest {
    
    SVDSimilarityCalculator sVDSimilarityCalculator;
    
    @Before
    public void setup(){
	this.sVDSimilarityCalculator = new SVDSimilarityCalculatorImpl();
    }
    
    @Test
    public void testEqualProfiles(){
	PersonDTO person1 = new PersonDTO();
	List<String> interestedKeywords = new ArrayList<String>();
	interestedKeywords.add("film");
	interestedKeywords.add("terminator");
	interestedKeywords.add("movie");
	interestedKeywords.add("film");
	interestedKeywords.add("gaming");
	interestedKeywords.add("music");
	interestedKeywords.add("movie");
	interestedKeywords.add("film");
	person1.setPersonInterestKeywords(interestedKeywords);
	
	PersonDTO person2 = new PersonDTO();
	List<String> interestedKeywords2 = new ArrayList<String>();
	interestedKeywords2.add("test");
	interestedKeywords2.add("hallo");
	interestedKeywords2.add("movie");
	interestedKeywords2.add("film");
	interestedKeywords2.add("match");
	interestedKeywords2.add("music");
	interestedKeywords2.add("movie");
	interestedKeywords2.add("film");
	person2.setPersonInterestKeywords(interestedKeywords2);
	
	PersonDTO person3 = new PersonDTO();
	List<String> interestedKeywords3 = new ArrayList<String>();
	interestedKeywords3.add("we");
	interestedKeywords3.add("hallo");
	interestedKeywords3.add("not");
	interestedKeywords3.add("similar");
	interestedKeywords3.add("equal");
	interestedKeywords3.add("lol");
	person3.setPersonInterestKeywords(interestedKeywords3);
	
	List<PersonDTO> persons = new ArrayList<PersonDTO>();
	persons.add(person2);
	persons.add(person3);
	
	sVDSimilarityCalculator.getXSimilarPersons(person1, persons, 1);
    }
    
}
