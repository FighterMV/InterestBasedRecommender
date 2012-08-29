/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
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
    public List<ItemDTO> getItemsOfUserFittingForUser(PersonDTO personToBeRecommended, PersonDTO similarUser) {
	List<ItemDTO> itemCandidates = similarUser.getProvidedItems();
	Set<ItemDTO> itemsToBeRecommended = new HashSet<ItemDTO>();
	
	for(ItemDTO itemCandidate : itemCandidates){
	    for(String itemKeyword : itemCandidate.getDescribingKeywords()){
		List<PersonInterestDTO> personInterests = personInterestService.getPersonInterests(personToBeRecommended);
		for(PersonInterestDTO personInterestDTO : personInterests){
		    if(personInterestDTO.getInterest().getName().equals(itemKeyword)){
			itemsToBeRecommended.add(itemCandidate);
		    }
		}
	    }
	}
	
	return new ArrayList<ItemDTO>(itemsToBeRecommended);
    }
    
}
