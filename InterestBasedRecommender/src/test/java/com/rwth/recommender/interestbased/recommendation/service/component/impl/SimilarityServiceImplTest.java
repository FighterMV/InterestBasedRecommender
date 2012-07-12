/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import com.rwth.recommender.interestbased.recommendation.service.component.FreebaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marco
 */
public class SimilarityServiceImplTest {
    
    private FreebaseService freebaseService;
    
    @Before
    public void setup(){
	freebaseService = new FreebaseServiceImpl();
    }
    
    @Test
    public void testFreebaseService(){
	List<String> test = freebaseService.getSimilarKeywords("film");
	List<String> keywords = new ArrayList<String>();
	keywords.add("men in black");
	List<String> mainTopics = freebaseService.getMainTopics(keywords);
	String test2 = "";
    }
    
}
