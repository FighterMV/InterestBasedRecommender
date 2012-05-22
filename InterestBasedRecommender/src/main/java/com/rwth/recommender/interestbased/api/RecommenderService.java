/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api;

import com.rwth.recommender.interestbased.dto.RecommendationDTO;
import com.rwth.recommender.interestbased.exception.UserModelNotValidException;

/**
 *
 * @author Marco
 */
public interface RecommenderService {
    
    public RecommendationDTO getRecommendations(String userModel) throws UserModelNotValidException;
    
}
