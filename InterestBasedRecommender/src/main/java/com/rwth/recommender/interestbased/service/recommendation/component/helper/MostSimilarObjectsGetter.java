/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper;

import Jama.Matrix;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marco
 */
public interface MostSimilarObjectsGetter<E> {
    
    public List<E> getXMostSimilarObjects(Map<E, Double> angleMap, int numberOfObjectsToReturn);
        
}
