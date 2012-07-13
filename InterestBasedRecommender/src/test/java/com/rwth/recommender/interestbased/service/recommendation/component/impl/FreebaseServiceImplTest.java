/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.service.recommendation.component.impl.FreebaseServiceImpl;
import com.rwth.recommender.interestbased.service.recommendation.component.FreebaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marco
 */
public class FreebaseServiceImplTest {
    
    private FreebaseService freebaseService;
    
    @Before
    public void setup(){
	freebaseService = new FreebaseServiceImpl();
    }
    
    @Test
    public void testFreebaseServiceSimilarWords(){
	List<String> similarKeywords = freebaseService.getSimilarKeywords("film");
	Assert.assertTrue(similarKeywords.contains("Action"));
    }
    
    @Test
    public void testFreebaseServiceMainTopics(){
	List<String> keywords = new ArrayList<String>();
	keywords.add("men in black");
	List<String> mainTopics = freebaseService.getMainTopics(keywords);
	Assert.assertTrue(mainTopics.contains("Film"));
    }
}
