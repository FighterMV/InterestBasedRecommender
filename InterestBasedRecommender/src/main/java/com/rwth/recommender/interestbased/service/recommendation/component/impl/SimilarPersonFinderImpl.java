/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.service.recommendation.component.SimilarPersonFinder;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineSimilarPersonFinder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SimilarPersonFinderImpl implements SimilarPersonFinder{
    
    @Autowired
    CosineSimilarPersonFinder cosineSimilarPersonFinder;
    
    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn) {
	return useCosineSimilarPersonFinder(person, persons, numberOfPersonsToReturn);
    }
    private List<PersonDTO> useCosineSimilarPersonFinder(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn){
	return cosineSimilarPersonFinder.getXSimilarPersons(person, persons, numberOfPersonsToReturn);
    }
    
}
