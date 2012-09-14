/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;

/**
 *
 * @author Marco
 */
public interface SVDSimilarityCalculator {
    
    public Boolean areSimilar(PersonDTO person1, PersonDTO person2);
    
}
