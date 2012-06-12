/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import com.rwth.recommender.interestbased.recommendation.service.component.helper.impl.SVDSimilarityCalculatorImpl;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.SVDSimilarityCalculator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
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
	interestedKeywords.add("shipment");
	interestedKeywords.add("of");
	interestedKeywords.add("gold");
	interestedKeywords.add("damaged");
	interestedKeywords.add("in");
	interestedKeywords.add("a");
	interestedKeywords.add("fire");
	person1.setPersonInterestKeywords(interestedKeywords);
	
	PersonDTO person2 = new PersonDTO();
	List<String> interestedKeywords2 = new ArrayList<String>();
	interestedKeywords2.add("delivery");
	interestedKeywords2.add("of");
	interestedKeywords2.add("silver");
	interestedKeywords2.add("arrived");
	interestedKeywords2.add("in");
	interestedKeywords2.add("a");
	interestedKeywords2.add("silver");
	interestedKeywords2.add("truck");
	person2.setPersonInterestKeywords(interestedKeywords2);
	
	PersonDTO person3 = new PersonDTO();
	List<String> interestedKeywords3 = new ArrayList<String>();
	interestedKeywords3.add("shipment");
	interestedKeywords3.add("of");
	interestedKeywords3.add("gold");
	interestedKeywords3.add("arrived");
	interestedKeywords3.add("in");
	interestedKeywords3.add("a");
	interestedKeywords3.add("truck");
	person3.setPersonInterestKeywords(interestedKeywords3);
	
	PersonDTO person4 = new PersonDTO();
	List<String> interestedKeywords4 = new ArrayList<String>();
	interestedKeywords4.add("gold");
	interestedKeywords4.add("silver");
	interestedKeywords4.add("truck");
	person4.setPersonInterestKeywords(interestedKeywords4);
	
	
	List<PersonDTO> persons = new ArrayList<PersonDTO>();
	persons.add(person1);
	persons.add(person2);
	persons.add(person3);
	
	List<PersonDTO> similarPersons = sVDSimilarityCalculator.getXSimilarPersons(person4, persons, 1);
	
	Assert.assertEquals(person2, similarPersons.get(0));
    }
        
}
