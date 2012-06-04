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
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.*;
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
    
    @Override
    public List<String> findSimilarKeywords(String keyword) {
	
	List<String> keywords = new ArrayList<String>();	
	
	System.setProperty("wordnet.database.dir", "C:/Program Files (x86)/WordNet/2.1/dict");
	WordNetDatabase database = WordNetDatabase.getFileInstance();
	
	Synset[] synsets = database.getSynsets(keyword);
	for(Synset synset : synsets){
	    keywords.addAll(Arrays.asList(synset.getWordForms()));
	}
	
	return keywords;
    }

    @Override
    public int calculateSimilarity(PersonDTO person1, PersonDTO person2) {
	//!TODO Implement better comparison
	int equalInterestKeywords = 0;
	for(String keyword : person1.getPersonInterestKeywords()){
	    if(person2.getPersonInterestKeywords().contains(keyword)){
		equalInterestKeywords += 1;
	    }
	}
	return equalInterestKeywords;
    }

    @Override
    public List<PersonDTO> findSimilarPersons(PersonDTO user) {
	LOGGER.trace("Starting to find similar persons");
	List<PersonDTO> similarUsers = new ArrayList<PersonDTO>();
	LOGGER.trace("Starting to get existing users");
	List<PersonDTO> existingUsers = personService.getList();
	for(PersonDTO userToCompare : existingUsers){
	    int similarityScore = calculateSimilarity(user, userToCompare);
	    if(similarityScore >= Constants.MINIMAL_SCORE_TO_BE_SIMILAR_USERS){
		similarUsers.add(userToCompare);
	    }
	}
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
