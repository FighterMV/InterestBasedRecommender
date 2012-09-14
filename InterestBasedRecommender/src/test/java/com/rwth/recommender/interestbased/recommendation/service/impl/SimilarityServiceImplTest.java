/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marco
 */
public class SimilarityServiceImplTest {
   
    private SimilarityService similarityService;
    
    @Before
    public void setup(){
	similarityService = new SimilarityServiceImpl();
	System.setProperty("wordnet.database.dir", "C:/Program Files (x86)/WordNet/2.1/dict");
    }
    
    @Test
    public void testSimilarityService(){
	List<String> similarWords = similarityService.findSimilarKeywords("film");
	
	Assert.assertTrue(similarWords.contains("film"));
	Assert.assertTrue(similarWords.contains("movie"));
    }
    
}
