/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.web.controller;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.*;
import com.rwth.recommender.interestbased.service.recommendation.RecommendationService;
import com.rwth.recommender.interestbased.service.recommendation.SimilarityService;
import com.rwth.recommender.interestbased.service.recommendation.component.FreebaseService;
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
    
    
    @RequestMapping(value = "/store-and-recommend-user", method = RequestMethod.POST)
    public ModelAndView storeAndRecommendUser(@ModelAttribute("storeAndRecommendUserModel")StoreAndRecommendUserModel model){
	
	PersonDTO personDTO = getPersonDTO(model);
	List<PersonInterestDTO> personInterestDTOs = getPersonInterestDTOs(model, personDTO);
		
	RecommendationDTO recommendation = recommendationService.getRecommendations(personDTO, personInterestDTOs);
	
	ModelAndView modelAndView = new ModelAndView("recommendation");
	modelAndView.addObject("username", recommendation.getPerson().getName());
	modelAndView.addObject("recommendation", recommendation);
	
	return modelAndView;
    }
    
    private List<PersonInterestDTO> getPersonInterestDTOs(StoreAndRecommendUserModel model, PersonDTO personDTO){
	String interests = model.getInterests().replace(" ", "");
	List<String> interestList = Arrays.asList(interests.split(Constants.INTEREST_SEPARATOR));
	
	String weightings = model.getWeightings().replace(" ", "");
	List<String> weightingListString = Arrays.asList(weightings.split(Constants.INTEREST_SEPARATOR));
	List<Integer> weightingList = new ArrayList<Integer>();
	
	for(String weightingString : weightingListString){
	    weightingList.add(Integer.parseInt(weightingString));
	}
	
	List<PersonInterestDTO> personInterestDTOs = new ArrayList<PersonInterestDTO>();
	
	for(int i = 0; i < interestList.size(); i++){
	    PersonInterestDTO personInterestDTO = new PersonInterestDTO();
	    InterestDTO interestDTO = new InterestDTO();
	    interestDTO.setName(interestList.get(i));
	    personInterestDTO.setInterest(interestDTO);
	    personInterestDTO.setPerson(personDTO);
	    personInterestDTO.setWeighting(weightingList.get(i));
	    personInterestDTOs.add(personInterestDTO);
	}
	
	return personInterestDTOs;
	
    }
    
    private PersonDTO getPersonDTO(StoreAndRecommendUserModel model){
	PersonDTO personDTO = new PersonDTO();
	personDTO.setName(model.getUsername());
	personDTO.setLink(model.getUserlink());
	
	List<PersonInterestDTO> personInterests = getPersonInterestDTOs(model, personDTO);
			
	String itemNames = model.getItemNames().replace(" ", "");
	List<String> itemNameList = Arrays.asList(itemNames.split(Constants.INTEREST_SEPARATOR));
	
	String itemLinks = model.getItemLinks().replace(" ", "");
	List<String> itemLinkList = Arrays.asList(itemLinks.split(Constants.INTEREST_SEPARATOR));
	
	
	List<List<String>> itemKeywords = new ArrayList<List<String>>();
	String rawItemKeywords = model.getItemKeywords().replace(" ", "");
	List<String> items = Arrays.asList(rawItemKeywords.split(Constants.ITEM_SEPARATOR));
	for(String currentItem : items){
	    List<String> currentItemKeywords = Arrays.asList(currentItem.split(Constants.INTEREST_SEPARATOR));
	    itemKeywords.add(currentItemKeywords);
	}	
	
	
	List<ItemDTO> providedItems = new ArrayList<ItemDTO>();
	
	for(int i = 0; i < itemNameList.size(); i++){
	    ItemDTO itemDTO = new ItemDTO();
	    itemDTO.setName(itemNameList.get(i));
	    itemDTO.setLink(itemLinkList.get(i));
	    itemDTO.setDescribingKeywords(itemKeywords.get(i));
	    providedItems.add(itemDTO);
	}
	
	personDTO.setProvidedItems(providedItems);
	
	return personDTO;
    }
    
}
