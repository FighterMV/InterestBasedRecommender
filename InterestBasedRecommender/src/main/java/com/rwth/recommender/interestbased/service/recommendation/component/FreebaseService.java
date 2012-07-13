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
public interface FreebaseService {
    
    /**
     * 
     * @param keyword
     * @return A list of similar keywords using the freebase api
     */
    public List<String> getSimilarKeywords(String keyword);
    
    /**
     * 
     * @param keywords
     * @return A list of title topics - eg for "bible" the resulting list would contain "book" and "religion"
     */
    public List<String> getMainTopics(List<String> keywords);
    
}
