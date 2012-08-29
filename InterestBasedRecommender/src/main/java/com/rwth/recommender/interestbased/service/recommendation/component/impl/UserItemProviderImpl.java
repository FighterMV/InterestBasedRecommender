/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.service.recommendation.component.UserItemProvider;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class UserItemProviderImpl implements UserItemProvider{

    @Override
    public List<ItemDTO> getItemsOfUserFittingForUser(PersonDTO userToBeRecommended, PersonDTO similarUser) {
	List<ItemDTO> itemCandidates = similarUser.getProvidedItems();
	List<ItemDTO> itemsToBeRecommended = new ArrayList<ItemDTO>();
	
	for(ItemDTO itemCandidate : itemCandidates){
	    for(String itemKeyword : itemCandidate.getDescribingKeywords()){
		if(userToBeRecommended.getInterestKeywords().contains(itemKeyword)){
		    itemsToBeRecommended.add(itemCandidate);
		}
	    }
	}
	
	return itemsToBeRecommended;
    }
    
}
