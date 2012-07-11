/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.recommendation.service.component.WordnetService;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class WordnetServiceImpl implements WordnetService{

    @Override
    public List<String> findSimilarKeywords(String keyword) {
	List<String> keywords = new ArrayList<String>();	
	
	System.setProperty("wordnet.database.dir", Constants.WORDNET_FOLDER);
	WordNetDatabase database = WordNetDatabase.getFileInstance();
	
	Synset[] synsets = database.getSynsets(keyword);
	for(Synset synset : synsets){
	    keywords.addAll(Arrays.asList(synset.getWordForms()));
	}
	
	return removeDoubleEntries(keywords);
    }
    
    private List<String> removeDoubleEntries(List<String> keywords){
	List<String> updatedList = new ArrayList<String>();
	for(String keyword : keywords){
	    if(!isInList(updatedList, keyword)){
		updatedList.add(keyword);
	    }
	}
	return updatedList;
    }
    
    private Boolean isInList(List<String> keywords, String newEntry){
	for(String keyword : keywords){
	    if(keyword.equals(newEntry)){
		return true;
	    }
	}
	return false;
    }
    
}
