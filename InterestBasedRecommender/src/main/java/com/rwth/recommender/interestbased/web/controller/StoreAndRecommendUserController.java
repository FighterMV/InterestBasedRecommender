/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.web.controller;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;
import com.rwth.recommender.interestbased.recommendation.service.RecommendationService;
import com.rwth.recommender.interestbased.web.model.StoreAndRecommendUserModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	
	UserDTO userDTO = getUserDTO(model);
	UserRecommendationDTO recommendation = recommendationService.getRecommendations(userDTO);
	
	ModelAndView modelAndView = new ModelAndView("recommendation");
	modelAndView.addObject("username", recommendation.getUser().getName());
	modelAndView.addObject("recommendations", recommendation.getRecommendedItems());
	
	return modelAndView;
    }
    
    
    private UserDTO getUserDTO(StoreAndRecommendUserModel model){
	UserDTO userDTO = new UserDTO();
	userDTO.setName(model.getUsername());
	
	String interests = model.getInterests().replace(" ", "");
	List<String> interestList = Arrays.asList(interests.split(Constants.INTEREST_SEPARATOR));
	
	String weightings = model.getWeightings().replace(" ", "");
	List<String> weightingListString = Arrays.asList(weightings.split(Constants.INTEREST_SEPARATOR));
	List<Integer> weightingList = new ArrayList<Integer>();
	for(String weightingString : weightingListString){
	    weightingList.add(Integer.getInteger(weightingString));
	}
	
	Map<InterestDTO, Integer> weightedInterests = new HashMap<InterestDTO, Integer>();
	for(int i = 0; i < interestList.size(); i++){
	    InterestDTO interestDTO = new InterestDTO();
	    interestDTO.setName(interestList.get(i));
	    weightedInterests.put(interestDTO, weightingList.get(i));
	}
	return userDTO;
    }
    
}
