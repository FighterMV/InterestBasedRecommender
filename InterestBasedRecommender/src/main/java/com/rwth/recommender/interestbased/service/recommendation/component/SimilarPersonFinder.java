/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface SimilarPersonFinder {
    
    /**
     * Receives a person A and a list of persons and calculated and calculates a List of the x most similar persons to A
     * @param person
     * @param persons
     * @param numberOfPersonsToReturn
     * @return 
     */
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn);
    
}
