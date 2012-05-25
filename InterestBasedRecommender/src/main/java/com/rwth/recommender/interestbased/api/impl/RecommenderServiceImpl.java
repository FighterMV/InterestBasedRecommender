/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api.impl;

import com.rwth.recommender.interestbased.api.RecommenderService;
import com.rwth.recommender.interestbased.api.component.validation.UserModelValidator;
import com.rwth.recommender.interestbased.api.dto.RecommendationDTO;
import com.rwth.recommender.interestbased.api.exception.UserModelNotValidException;
import com.rwth.recommender.interestbased.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class RecommenderServiceImpl implements RecommenderService{

    @Autowired
    UserModelValidator userModelValidator;
    
    @Autowired
    RecommendationService recommendationService;
    
    @Override
    public RecommendationDTO getRecommendations(String userModel) throws UserModelNotValidException {
	
	if(!userModelValidator.isValidModel(userModel)){
	    throw new UserModelNotValidException("The provided userModel is not valid");
	}
		
	//!TODO Go on implementing
	
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
