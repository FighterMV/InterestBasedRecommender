/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Marco
 */
public interface CosineCalculator<E> {
      
    public double getAngle(List<String> interestingKeywords1, List<String> interestingKeywords2);
    
    public List<E> getXMostSimilarObjects(Map<E, Double> angleMap, int numberOfObjectsToReturn);
    	    
}