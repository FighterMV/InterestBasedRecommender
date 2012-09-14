/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service;

import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Marco
 */
public interface ItemRecommendationService {
    
    void storeInDatabase(List<ItemRecommendationDTO> itemRecommendations);
    
}
