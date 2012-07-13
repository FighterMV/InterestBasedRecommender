/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper.impl;

import Jama.Matrix;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.service.InterestService;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineCalculator;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineSimilarPersonFinder;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.MostSimilarObjectsGetter;
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
    
    @Autowired
    InterestService interestService;
    
    @Autowired
    MostSimilarObjectsGetter mostSimilarObjectsGetter;

    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn) {
	
	Map<PersonDTO, Double> candidateAngleMap = new HashMap<PersonDTO, Double>();
	
	List<InterestDTO> personInterests = interestService.getInterests(person);
	
	for(PersonDTO candidate : persons){
	    List<InterestDTO> candidateInterests = interestService.getInterests(candidate);
	    Double angle = cosineCalculator.getInterestsAngle(personInterests, candidateInterests);
	    candidateAngleMap.put(candidate, angle);
	}
	
	List<PersonDTO> mostSimilarPersons = mostSimilarObjectsGetter.getXMostSimilarObjects(candidateAngleMap, numberOfPersonsToReturn);
	
	return mostSimilarPersons;
    }
    
    
}
