/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.impl;

import com.rwth.recommender.interestbased.recommendation.service.component.FreebaseService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<String> getSimilarKeywords(String keyword) {
	URL url = null;
	String output = "";
	try {
	    String urlString = "https://www.googleapis.com/freebase/v1/search?query=" + keyword;
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
	
	List<String> similarKeywords = new ArrayList<String>();
	
	JSONObject json = (JSONObject) JSONSerializer.toJSON(output);
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
    
}
