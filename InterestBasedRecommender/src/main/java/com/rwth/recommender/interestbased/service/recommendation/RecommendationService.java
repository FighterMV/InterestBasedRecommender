/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.RecommendationDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface RecommendationService {
    
    /**
     * 
     * @param person The person who wants a recommendation
     * @param interestDTOs A list of interests of the person
     * @return A recommendation with items the user may be interested in
     */
    public RecommendationDTO getRecommendations(PersonDTO person, List<InterestDTO> interestDTOs);
    
}
