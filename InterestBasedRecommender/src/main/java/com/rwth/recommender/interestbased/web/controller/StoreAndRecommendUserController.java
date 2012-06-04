/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.web.controller;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.*;
import com.rwth.recommender.interestbased.recommendation.service.RecommendationService;
import com.rwth.recommender.interestbased.recommendation.service.SimilarityService;
import com.rwth.recommender.interestbased.web.model.StoreAndRecommendUserModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marco
 */
@Controller
public class StoreAndRecommendUserController {
    
    @Autowired
    RecommendationService recommendationService;
    
    @Autowired
    SimilarityService similarityService;
    
    
    @RequestMapping(value = "/store-and-recommend-user", method = RequestMethod.POST)
    public ModelAndView storeAndRecommendUser(@ModelAttribute("storeAndRecommendUserModel")StoreAndRecommendUserModel model){
	
	PersonDTO personDTO = getPersonDTO(model);
	List<InterestDTO> interestDTOs = getInterestDTOs(model, personDTO);
	
	RecommendationDTO recommendation = recommendationService.getRecommendations(personDTO, interestDTOs);
	
	ModelAndView modelAndView = new ModelAndView("recommendation");
	modelAndView.addObject("username", recommendation.getPerson().getName());
	modelAndView.addObject("recommendations", recommendation.getItemRecommendations());
	
	return modelAndView;
    }
    
    private List<InterestDTO> getInterestDTOs(StoreAndRecommendUserModel model, PersonDTO personDTO){
	String interests = model.getInterests().replace(" ", "");
	List<String> interestList = Arrays.asList(interests.split(Constants.INTEREST_SEPARATOR));
	
	String weightings = model.getWeightings().replace(" ", "");
	List<String> weightingListString = Arrays.asList(weightings.split(Constants.INTEREST_SEPARATOR));
	List<Integer> weightingList = new ArrayList<Integer>();
	
	for(String weightingString : weightingListString){
	    weightingList.add(Integer.parseInt(weightingString));
	}
	
	List<InterestDTO> weightedInterests = new ArrayList<InterestDTO>();
	for(int i = 0; i < interestList.size(); i++){
	    InterestDTO interestDTO = new InterestDTO();
	    interestDTO.setName(interestList.get(i));
	    interestDTO.setPerson(personDTO);
	    interestDTO.setWeighting(weightingList.get(i));
	    weightedInterests.add(interestDTO);
	}
	
	return weightedInterests;
	
    }
    
    private PersonDTO getPersonDTO(StoreAndRecommendUserModel model){
	PersonDTO personDTO = new PersonDTO();
	personDTO.setName(model.getUsername());
	
	List<InterestDTO> weightedInterests = getInterestDTOs(model, personDTO);
		
	List<String> interestKeywords = similarityService.getInterestKeywords(weightedInterests);
	personDTO.setPersonInterestKeywords(interestKeywords);
	
	String itemNames = model.getItemNames().replace(" ", "");
	List<String> itemNameList = Arrays.asList(itemNames.split(Constants.INTEREST_SEPARATOR));
	
	String itemLinks = model.getItemLinks().replace(" ", "");
	List<String> itemLinkList = Arrays.asList(itemLinks.split(Constants.INTEREST_SEPARATOR));
	
	List<ItemRecommendationDTO> providedItems = new ArrayList<ItemRecommendationDTO>();
	
	for(int i = 0; i < itemNameList.size(); i++){
	    ItemDTO itemDTO = new ItemDTO();
	    itemDTO.setName(itemNameList.get(i));
	    itemDTO.setLink(itemLinkList.get(i));
	    ItemRecommendationDTO itemRecommendationDTO = new ItemRecommendationDTO();
	    itemRecommendationDTO.setItem(itemDTO);
	    itemRecommendationDTO.setAccuracy(100);
	    providedItems.add(itemRecommendationDTO);
	}
	
	personDTO.setItemRecommendations(providedItems);
	
	return personDTO;
    }
    
}
