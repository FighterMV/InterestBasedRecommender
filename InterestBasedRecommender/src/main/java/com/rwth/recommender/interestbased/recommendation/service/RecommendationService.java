/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.RecommendationDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface RecommendationService {
    
    public RecommendationDTO getRecommendations(PersonDTO person, List<InterestDTO> interestDTOs);
    
}
