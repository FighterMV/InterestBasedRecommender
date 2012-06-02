/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Marco
 */
public interface SimilarityService {
    
    public List<String> findSimilarKeywords(String keyword);
    
    public int calculateSimilarity(UserDTO user1, UserDTO user2);
    
    public List<UserDTO> findSimilarUsers(UserDTO user);
    
    public Set<String> getInterestKeywords(Map<InterestDTO, Integer> weightedInterests);
    
}
