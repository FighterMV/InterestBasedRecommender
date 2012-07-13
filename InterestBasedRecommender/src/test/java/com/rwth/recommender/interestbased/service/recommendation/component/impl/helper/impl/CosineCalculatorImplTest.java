/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl.helper.impl;

import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineCalculator;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.impl.CosineCalculatorImpl;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marco
 */
public class CosineCalculatorImplTest {
    
    private CosineCalculator cosineCalculator;
    
    @Before
    public void setup(){
	cosineCalculator = new CosineCalculatorImpl();
    }
    
    @Test
    public void testKeywordCosine(){
	List<String> keywords1 = new ArrayList<String>();
	keywords1.add("this");
	keywords1.add("is");
	keywords1.add("the");
	keywords1.add("keyword");
	keywords1.add("test");
	
	List<String> keywords2 = new ArrayList<String>();
	keywords2.add("keyword");
	keywords2.add("test");
	keywords2.add("this");
	
	double cosine = cosineCalculator.getKeywordsAngle(keywords1, keywords2);
	
	Assert.assertEquals(cosine, 0.7227342478134157);
    }
    
}
