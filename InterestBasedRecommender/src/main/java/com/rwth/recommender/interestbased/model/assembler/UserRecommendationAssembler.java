/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.UserRecommendation;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class UserRecommendationAssembler {
    
    @Autowired
    UserAssembler userAssembler;
    
    @Autowired
    ItemRecommendationAssembler itemRecommendationAssembler;
        
    public UserRecommendationDTO assembleDTO(UserRecommendation userRecommendation){
	UserRecommendationDTO userRecommendationDTO = new UserRecommendationDTO();
	userRecommendationDTO.setUser(userAssembler.assembleDTO(userRecommendation.getUser()));
	userRecommendationDTO.setRecommendedItems(itemRecommendationAssembler.assembleDTOList(userRecommendation.getRecommendedItems()));
	userRecommendationDTO.setId(userRecommendation.getId());
	return userRecommendationDTO;
    }
    
    public UserRecommendation assemble(UserRecommendationDTO userRecommendationDTO){
	UserRecommendation userRecommendation = new UserRecommendation();
	userRecommendation.setId(userRecommendationDTO.getId());
	userRecommendation.setUser(userAssembler.assemble(userRecommendationDTO.getUser()));
	userRecommendation.setRecommendedItems(itemRecommendationAssembler.assembleList(userRecommendationDTO.getRecommendedItems()));
	return userRecommendation;
    }
    
}
