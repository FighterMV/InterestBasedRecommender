/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface SimpleSimilarPersonFinder {

    public List<PersonDTO> findXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn);
    
}
