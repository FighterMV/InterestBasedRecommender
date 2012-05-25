/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.impl;

import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.service.UserService;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SimilarityServiceImpl implements SimilarityService{

    public static int MINIMAL_SCORE_TO_BE_SIMILAR_USERS = 10;
    
    @Autowired
    UserService userService;
    
    @Override
    public List<String> findSimilarKeywords(String keyword) {
	
	List<String> keywords = new ArrayList<String>();
	
	WordNetDatabase database = WordNetDatabase.getFileInstance();
	Synset[] synsets = database.getSynsets(keyword);
	for(Synset synset : synsets){
	    keywords.addAll(Arrays.asList(synset.getWordForms()));
	}
	
	return keywords;
    }

    @Override
    public int calculateSimilarity(UserDTO user1, UserDTO user2) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserDTO> findSimilarUsers(UserDTO user) {
	List<UserDTO> similarUsers = new ArrayList<UserDTO>();
	List<UserDTO> existingUsers = userService.getList();
	for(UserDTO userToCompare : existingUsers){
	    int similarityScore = calculateSimilarity(user, userToCompare);
	    if(similarityScore >= MINIMAL_SCORE_TO_BE_SIMILAR_USERS){
		similarUsers.add(userToCompare);
	    }
	}
	return similarUsers;
    }
    
}
