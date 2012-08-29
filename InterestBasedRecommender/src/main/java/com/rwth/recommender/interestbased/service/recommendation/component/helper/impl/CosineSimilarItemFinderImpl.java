/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper.impl;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineCalculator;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineSimilarItemFinder;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.MostSimilarObjectsGetter;
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
public class CosineSimilarItemFinderImpl implements CosineSimilarItemFinder{

    @Autowired
    CosineCalculator cosineCalculator;
    
    @Autowired
    MostSimilarObjectsGetter mostSimilarObjectsGetter;
    
    @Autowired
    PersonInterestService personInterestService;
    
    @Override
    public List<ItemDTO> getXSimilarItems(List<ItemDTO> items, PersonDTO person, int numberOfItemsToReturn) {
	Map<ItemDTO, Double> itemAngleMap = new HashMap<ItemDTO, Double>();
	
	List<String> personInterestKeywords = new ArrayList<String>();
	for(PersonInterestDTO personInterest : personInterestService.getPersonInterests(person)){
	    personInterestKeywords.add(personInterest.getInterest().getName());
	}
	
	for(ItemDTO item : items){
	    Double angle = cosineCalculator.getKeywordsAngle(personInterestKeywords, item.getDescribingKeywords());
	    itemAngleMap.put(item, angle);
	}
	
	List<ItemDTO> mostSimilarItems = mostSimilarObjectsGetter.getXMostSimilarObjects(itemAngleMap, numberOfItemsToReturn);
	
	return mostSimilarItems;
    }
    
}
