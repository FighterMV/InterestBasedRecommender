/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.service.recommendation.component.FreebaseService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class FreebaseServiceImpl implements FreebaseService{

    private static String API_KEY = "AIzaSyB4M19NvSIhCgjb3C5y9SLZWoI0ib-sgO4";
    
    @Override
    public List<String> getSimilarKeywords(String keyword) {
	
	JSONObject json = getJSONObject(keyword);
	
	List<String> similarKeywords = new ArrayList<String>();
	
	JSONArray resultArray = json.getJSONArray("result");
	for(int i = 0; i < resultArray.size(); i++){
	    JSONObject currentResult = (JSONObject)resultArray.get(i);
	    String name = currentResult.getString("name");
	    if(!contains(similarKeywords, name)){
		similarKeywords.add(name);
	    }
	}
	
	return similarKeywords;
    }
    
    private Boolean contains(List<String> keywords, String keyword){
	for(String currentKeyword : keywords){
	    if(currentKeyword.equals(keyword)){
		return true;
	    }
	}
	return false;
    }

    @Override
    public List<String> getMainTopics(List<String> keywords) {
	Set<String> mainTopics = new HashSet<String>();
	for(String keyword : keywords){
	    mainTopics.addAll(getMainTopics(keyword));
	}
	
	return new ArrayList<String>(mainTopics);
    }

    
    private Set<String> getMainTopics(String keyword) {
	
	String requestKeyword = keyword.replaceAll(" ", "_");
	
	JSONObject json = getJSONObject(requestKeyword);
	
	Set<String> mainTopics = new HashSet<String>();	
	
	if(json.containsKey("result")){
	    JSONArray resultArray = json.getJSONArray("result");
	    for(int i = 0; i < resultArray.size(); i++){
		JSONObject currentResult = (JSONObject)resultArray.get(i);
		if(currentResult.containsKey("score")){
		    if(currentResult.getDouble("score") > Constants.MIN_SCORE_TO_BE_MAIN_TOPIC){
			if(currentResult.containsKey("notable")){
			    JSONObject notable = currentResult.getJSONObject("notable");
			    if(notable.containsKey("name")){
				mainTopics.add(notable.getString("name"));
			    }
			}
		    }
		}		
	    }
	}
	return mainTopics;
    }

    private JSONObject getJSONObject(String keyword) {
	URL url = null;
	String output = "";
	try {
	    String urlString = "https://www.googleapis.com/freebase/v1/search?query=" + keyword.replaceAll(" ", "_") + "&key=" + API_KEY;
	    url = new URL(urlString);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	    
	    String currentLine = "";
	    while((currentLine = reader.readLine()) != null){
		output += currentLine;
	    }
	} catch (MalformedURLException ex) {
	    Logger.getLogger(FreebaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
	}catch (IOException ex) {
	    Logger.getLogger(FreebaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
	JSONObject json = (JSONObject) JSONSerializer.toJSON(output);
	return json;
    }
    
}
