/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.User;
import com.rwth.recommender.interestbased.model.database.WeightedInterest;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import java.util.ArrayList;
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
    
    public UserDTO assembleDTO(User user){
	UserDTO userDTO = new UserDTO();
	userDTO.setId(user.getId());
	userDTO.setLastRecommendationDate(user.getLastRecommendationDate());
	userDTO.setName(user.getName());
	userDTO.setWeightedInterests(assembleWeightedInterestMap(user.getWeightedInterests()));
	return userDTO;
    }
    
    public List<UserDTO> assembleDTOList(List<User> userList){
	List<UserDTO> userDTOs = new ArrayList<UserDTO>();
	for(User user : userList){
	    userDTOs.add(assembleDTO(user));
	}
	return userDTOs;
    }
    
    
    private Map<InterestDTO, Integer> assembleWeightedInterestMap(List<WeightedInterest> weightedInterests){
	Map<InterestDTO, Integer> interestMap = new HashMap<InterestDTO, Integer>();
	for(WeightedInterest weightedInterest : weightedInterests){
	    interestMap.put(interestAssembler.assembleDTO(weightedInterest.getInterest()), weightedInterest.getWeighting());
	}
	return interestMap;
    }
    
    public User assemble(UserDTO userDTO){
	User user = new User();
	user.setLastRecommendationDate(userDTO.getLastRecommendationDate());
	user.setId(userDTO.getId());
	user.setName(user.getName());
	user.setUserInterestKeywords(user.getUserInterestKeywords());
	user.setWeightedInterests(assembleWeightedInterestList(userDTO.getWeightedInterests()));
	return user;
    }
    
    private List<WeightedInterest> assembleWeightedInterestList(Map<InterestDTO, Integer> interestMap){
	List<WeightedInterest> weightedInterests = new ArrayList<WeightedInterest>();
	for(InterestDTO interestDTO : interestMap.keySet()){
	    WeightedInterest weightedInterest = new WeightedInterest();
	    weightedInterest.setInterest(interestAssembler.assemble(interestDTO));
	    weightedInterest.setWeighting(interestMap.get(interestDTO));
	}
	return weightedInterests;
    }
    
}
