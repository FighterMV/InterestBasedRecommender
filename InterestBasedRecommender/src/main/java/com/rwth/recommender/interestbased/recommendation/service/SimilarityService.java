/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface SimilarityService {
    
    public List<String> findSimilarKeywords(String keyword);
        
    public List<PersonDTO> findSimilarPersons(PersonDTO person);
    
    public List<ItemDTO> findSimilarItems(List<ItemDTO> items, PersonDTO person);
    
    public List<String> getInterestKeywords(List<InterestDTO> weightedInterests);
    
}
