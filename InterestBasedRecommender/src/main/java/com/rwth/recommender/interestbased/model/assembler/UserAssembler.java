/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.User;
import com.rwth.recommender.interestbased.model.database.WeightedInterest;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class UserAssembler {
    
    @Autowired
    private InterestAssembler interestAssembler;
    
    public UserDTO getDTO(User user){
	UserDTO userDTO = new UserDTO();
	userDTO.setId(user.getId());
	userDTO.setLastRecommendationDate(user.getLastRecommendationDate());
	userDTO.setName(user.getName());
	userDTO.setWeightedInterests(assembleWeightedInterestMap(user.getWeightedInterests()));
	return userDTO;
    }
    
    
    private Map<InterestDTO, Integer> assembleWeightedInterestMap(List<WeightedInterest> weightedInterests){
	Map<InterestDTO, Integer> interestMap = new HashMap<InterestDTO, Integer>();
	for(WeightedInterest weightedInterest : weightedInterests){
	    interestMap.put(interestAssembler.getDTO(weightedInterest.getInterest()), weightedInterest.getWeighting());
	}
	return interestMap;
    }
    
}
