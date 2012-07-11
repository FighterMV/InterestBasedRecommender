/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component;

import java.util.List;

/**
 *
 * @author Marco
 */
public interface WordnetService {
    
    public List<String> findSimilarKeywords(String keyword);
    
}
