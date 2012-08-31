/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
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
    public List<PersonInterestDTO> getSimilarInterests(PersonInterestDTO personInterestDTO) {
	
	JSONObject json = getJSONObject(personInterestDTO.getInterest().getName());
	
	List<PersonInterestDTO> similarPersonInterestDTOs = new ArrayList<PersonInterestDTO>();
	similarPersonInterestDTOs.add(personInterestDTO);
	
	JSONArray resultArray = json.getJSONArray("result");
	
	JSONObject bestResult = (JSONObject) resultArray.get(0);
	double maxScore = bestResult.getDouble("score");
	
	for(int i = 0; i < resultArray.size(); i++){
	    JSONObject currentResult = (JSONObject)resultArray.get(i);
	    String name = currentResult.getString("name");
	    if(!contains(similarPersonInterestDTOs, name)){
		PersonInterestDTO newPersonInterestDTO = new PersonInterestDTO();
		newPersonInterestDTO.setPerson(personInterestDTO.getPerson());
		InterestDTO interest = new InterestDTO();
		interest.setName(name);
		newPersonInterestDTO.setInterest(interest);
		double score = currentResult.getDouble("score");
		if(score >= Constants.SCORE_FACTOR_FROM_FREEBASE_TO_BE_CONSIDERED * maxScore){
		    double newWeighting = (new Double(score) / new Double(maxScore)) * personInterestDTO.getWeighting();
		    newPersonInterestDTO.setWeighting((int) newWeighting);
		    similarPersonInterestDTOs.add(newPersonInterestDTO);
		}
	    }
	}
	
	return similarPersonInterestDTOs;
    }
    
    private Boolean contains(List<PersonInterestDTO> personInterestDTOs, String keyword){
	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    if(personInterestDTO.getInterest().getName().equals(keyword)){
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
	    JSONObject bestResult = resultArray.getJSONObject(0);
	    double maxScore = bestResult.getDouble("score");
	    for(int i = 0; i < resultArray.size(); i++){
		JSONObject currentResult = (JSONObject)resultArray.get(i);
		if(currentResult.containsKey("score")){
		    if(currentResult.getDouble("score") >= Constants.SCORE_FACTOR_FROM_FREEBASE_TO_BE_CONSIDERED * maxScore){
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
