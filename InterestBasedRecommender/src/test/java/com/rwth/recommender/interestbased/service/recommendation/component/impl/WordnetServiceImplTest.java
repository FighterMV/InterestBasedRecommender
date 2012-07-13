/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.service.recommendation.component.WordnetService;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Marco
 */
public class WordnetServiceImplTest {
    
    @Test
    public void testWordnet(){
	WordnetService wordnetService = new WordnetServiceImpl();
	List<String> similarKeywords = wordnetService.findSimilarKeywords("film");
	Assert.assertTrue(similarKeywords.contains("movie"));
    }
    
}
