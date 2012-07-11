/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import com.rwth.recommender.interestbased.recommendation.service.component.FreebaseService;
import java.util.List;
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
    }
    
}
