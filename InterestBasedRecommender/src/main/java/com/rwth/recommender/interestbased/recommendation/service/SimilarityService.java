/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service;

import java.util.List;

/**
 *
 * @author Marco
 */
public interface SimilarityService {
    
    public List<String> findSimilarKeywords(String keyword);
    
}
