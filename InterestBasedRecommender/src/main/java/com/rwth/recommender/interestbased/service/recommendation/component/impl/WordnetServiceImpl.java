/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.service.recommendation.component.WordnetService;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class WordnetServiceImpl implements WordnetService{

    @Override
    public List<String> findSimilarKeywords(String keyword) {
	Set<String> keywords = new HashSet<String>();	
	
	System.setProperty("wordnet.database.dir", Constants.WORDNET_FOLDER);
	WordNetDatabase database = WordNetDatabase.getFileInstance();
	
	Synset[] synsets = database.getSynsets(keyword);
	for(Synset synset : synsets){
	    keywords.addAll(Arrays.asList(synset.getWordForms()));
	}
	
	return new ArrayList<String>(keywords);
    }
    
}
