/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service;

import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;

/**
 *
 * @author Marco
 */
public interface RecommendationService {
    
    public UserRecommendationDTO getRecommendations(UserDTO user);
    
}
