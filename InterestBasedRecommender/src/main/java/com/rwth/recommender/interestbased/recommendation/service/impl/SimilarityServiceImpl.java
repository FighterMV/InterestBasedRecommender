/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.service.UserService;
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
    UserService userService;
    
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
    public int calculateSimilarity(UserDTO user1, UserDTO user2) {
	//!TODO Implement better comparison
	int equalInterestKeywords = 0;
	for(String keyword : user1.getUserInterestKeywords()){
	    if(user2.getUserInterestKeywords().contains(keyword)){
		equalInterestKeywords += 1;
	    }
	}
	return equalInterestKeywords;
    }

    @Override
    public List<UserDTO> findSimilarUsers(UserDTO user) {
	List<UserDTO> similarUsers = new ArrayList<UserDTO>();
	List<UserDTO> existingUsers = userService.getList();
	for(UserDTO userToCompare : existingUsers){
	    int similarityScore = calculateSimilarity(user, userToCompare);
	    if(similarityScore >= Constants.MINIMAL_SCORE_TO_BE_SIMILAR_USERS){
		similarUsers.add(userToCompare);
	    }
	}
	return similarUsers;
    }
    
    @Override
    public Set<String> getInterestKeywords(Map<InterestDTO, Integer> weightedInterests){
	Set<String> userInterestKeywords = new HashSet<String>();
	for(InterestDTO interest : weightedInterests.keySet()){
	    if(weightedInterests.get(interest) > Constants.MINIMUM_VALUE_TO_BE_GOOD_INTEREST){
		List<String> similarKeywords = findSimilarKeywords(interest.getName());
		userInterestKeywords.addAll(similarKeywords);
	    }
	}
	return userInterestKeywords;
    }
    
}
