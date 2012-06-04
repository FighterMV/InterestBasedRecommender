/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Marco
 */
public interface SimilarityService {
    
    public List<String> findSimilarKeywords(String keyword);
    
    public int calculateSimilarity(PersonDTO user1, PersonDTO user2);
    
    public List<PersonDTO> findSimilarPersons(PersonDTO person);
    
    public Set<String> getInterestKeywords(List<InterestDTO> weightedInterests);
    
}
