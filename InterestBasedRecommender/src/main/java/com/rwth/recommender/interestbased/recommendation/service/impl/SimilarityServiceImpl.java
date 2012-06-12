/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.service.PersonService;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import com.rwth.recommender.interestbased.recommendation.service.component.SimilarPersonFinder;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SimilarityServiceImpl implements SimilarityService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SimilarityServiceImpl.class);
    
    @Autowired
    PersonService personService;
    
    @Autowired
    SimilarPersonFinder similarPersonFinder;
    
    @Override
    public List<String> findSimilarKeywords(String keyword) {
	
	List<String> keywords = new ArrayList<String>();	
	
	System.setProperty("wordnet.database.dir", Constants.WORDNET_FOLDER);
	WordNetDatabase database = WordNetDatabase.getFileInstance();
	
	Synset[] synsets = database.getSynsets(keyword);
	for(Synset synset : synsets){
	    keywords.addAll(Arrays.asList(synset.getWordForms()));
	}
	
	return keywords;
    }

    @Override
    public List<PersonDTO> findSimilarPersons(PersonDTO user) {
	LOGGER.trace("Starting to find similar persons");
	LOGGER.trace("Starting to get existing users");
	List<PersonDTO> existingUsers = personService.getList();
	List<PersonDTO> similarUsers = similarPersonFinder.getXSimilarPersons(user, existingUsers, 5);
	return similarUsers;
    }
    
    @Override
    public List<String> getInterestKeywords(List<InterestDTO> weightedInterests){
	List<String> userInterestKeywords = new ArrayList<String>();
	for(InterestDTO interest : weightedInterests){
	    if(interest.getWeighting() > Constants.MINIMUM_VALUE_TO_BE_GOOD_INTEREST){
		List<String> similarKeywords = findSimilarKeywords(interest.getName());
		userInterestKeywords.addAll(similarKeywords);
	    }
	}
	return userInterestKeywords;
    }
    
}
