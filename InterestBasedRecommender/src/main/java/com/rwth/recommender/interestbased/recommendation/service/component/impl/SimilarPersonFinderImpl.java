/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.SimilarPersonFinder;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.CosineSimilarPersonFinder;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.SVDSimilarityCalculator;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.SimpleSimilarPersonFinder;
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
    SimpleSimilarPersonFinder simpleSimilarPersonFinder;
    
    @Autowired
    SVDSimilarityCalculator svdSimilarityCalculator;
    
    @Autowired
    CosineSimilarPersonFinder cosineSimilarPersonFinder;
    
    @Override
    public List<PersonDTO> getXSimilarPersons(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn) {
	return useSVDSimilarPersonFinder(person, persons, numberOfPersonsToReturn);
    }
    
    private List<PersonDTO> useSimpleSimilarPersonFinder(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn){
	return simpleSimilarPersonFinder.findXSimilarPersons(person, persons, numberOfPersonsToReturn);
    }
    
    private List<PersonDTO> useSVDSimilarPersonFinder(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn){
	return svdSimilarityCalculator.getXSimilarPersons(person, persons, numberOfPersonsToReturn);
    }
    
    private List<PersonDTO> useCosineSimilarPersonFinder(PersonDTO person, List<PersonDTO> persons, int numberOfPersonsToReturn){
	return cosineSimilarPersonFinder.getXSimilarPersons(person, persons, numberOfPersonsToReturn);
    }
    
}
