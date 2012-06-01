/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.UserRecommendation;
import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;
import java.util.List;
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
    
    public UserRecommendationDTO getDTO(UserRecommendation userRecommendation){
	UserRecommendationDTO userRecommendationDTO = new UserRecommendationDTO();
	userRecommendationDTO.setUser(userAssembler.getDTO(userRecommendation.getUser()));
	userRecommendationDTO.setRecommendedItems(itemRecommendationAssembler.getDTOList(userRecommendation.getRecommendedItems()));
	return userRecommendationDTO;
    }
    
}
