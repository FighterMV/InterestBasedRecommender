/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper.impl;

import Jama.Matrix;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.CosineCalculator;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.CosineSimilarPersonFinder;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class CosineSimilarPersonFinderImpl implements CosineSimilarPersonFinder{
    
    @Autowired
    private CosineCalculator cosineCalculator;

    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn) {
	
	Map<PersonDTO, Double> candidateAngleMap = new HashMap<PersonDTO, Double>();
	
	for(PersonDTO candidate : persons){
	    Double angle = cosineCalculator.getAngle(person.getPersonInterestKeywords(), candidate.getPersonInterestKeywords());
	    candidateAngleMap.put(candidate, angle);
	}
	
	List<PersonDTO> mostSimilarPersons = cosineCalculator.getXMostSimilarObjects(candidateAngleMap, numberOfPersonsToReturn);
	
	return mostSimilarPersons;
    }
    
    
}
