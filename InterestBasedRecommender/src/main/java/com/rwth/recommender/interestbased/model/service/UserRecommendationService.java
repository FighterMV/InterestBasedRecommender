/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service;

import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;

/**
 *
 * @author Marco
 */
public interface UserRecommendationService {
    
    public UserRecommendationDTO getRecommendationForUser(Long userId);
    
    public void storeInDatabase(UserRecommendationDTO userRecommendationDTO);
    
}
