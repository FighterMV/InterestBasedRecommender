/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.dto.UserMappingItemsDTO;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import com.rwth.recommender.interestbased.service.recommendation.component.UserItemProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class UserItemProviderImpl implements UserItemProvider{

    @Autowired
    PersonInterestService personInterestService;
    
    @Override
    public UserMappingItemsDTO orderItemsFittingForUser(PersonDTO personToBeRecommended, PersonDTO similarUser) {
	List<ItemDTO> itemCandidates = similarUser.getProvidedItems();
	Set<ItemDTO> itemsSimilarToRequestingUser = new HashSet<ItemDTO>();
	Set<ItemDTO> otherItems = new HashSet<ItemDTO>();
	
	for(ItemDTO itemCandidate : itemCandidates){
	    for(String itemKeyword : itemCandidate.getDescribingKeywords()){
		List<PersonInterestDTO> personInterests = personInterestService.getPersonInterests(personToBeRecommended);
		for(PersonInterestDTO personInterestDTO : personInterests){
		    if(personInterestDTO.getInterest().getName().equals(itemKeyword)){
			itemsSimilarToRequestingUser.add(itemCandidate);
		    }
		}
	    }
	    if(!containsItem(itemCandidate, itemsSimilarToRequestingUser)){
		otherItems.add(itemCandidate);
	    }
	}
	
	UserMappingItemsDTO userMappingItemsDTO = new UserMappingItemsDTO();
	userMappingItemsDTO.setMappingItems(itemsSimilarToRequestingUser);
	userMappingItemsDTO.setOtherItems(otherItems);
	
	return userMappingItemsDTO;
    }
    
    private Boolean containsItem(ItemDTO item, Set<ItemDTO> itemDTOs){
	for(ItemDTO itemDTO : itemDTOs){
	    if(item.getLink().equals(itemDTO.getLink())){
		return true;
	    }
	}
	return false;
    }
    
}
