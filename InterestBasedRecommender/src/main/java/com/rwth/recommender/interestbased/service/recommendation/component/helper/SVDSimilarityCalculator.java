/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface SVDSimilarityCalculator {
    
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> personDTOs, int numberOfUsersToReturn);
    
}
