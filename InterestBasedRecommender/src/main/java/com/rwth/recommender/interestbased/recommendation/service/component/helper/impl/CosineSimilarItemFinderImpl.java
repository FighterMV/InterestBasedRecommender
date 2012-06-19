/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component.helper.impl;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.CosineCalculator;
import com.rwth.recommender.interestbased.recommendation.service.component.helper.CosineSimilarItemFinder;
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
    
    @Override
    public List<ItemDTO> getXSimilarItems(List<ItemDTO> items, PersonDTO person, int numberOfItemsToReturn) {
	Map<ItemDTO, Double> itemAngleMap = new HashMap<ItemDTO, Double>();
	
	for(ItemDTO item : items){
	    Double angle = cosineCalculator.getAngle(person.getPersonInterestKeywords(), item.getDescribingKeywords());
	    itemAngleMap.put(item, angle);
	}
	
	List<ItemDTO> mostSimilarItems = cosineCalculator.getXMostSimilarObjects(itemAngleMap, numberOfItemsToReturn);
	
	return mostSimilarItems;
    }
    
}
