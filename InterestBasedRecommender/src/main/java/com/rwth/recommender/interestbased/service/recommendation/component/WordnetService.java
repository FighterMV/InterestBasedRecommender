/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component;

import java.util.List;

/**
 *
 * @author Marco
 */
public interface WordnetService {
    
    /**
     * Finds similar keywords to the provided keyword using wordnet
     * @param keyword
     * @return 
     */
    public List<String> findSimilarKeywords(String keyword);
    
}
