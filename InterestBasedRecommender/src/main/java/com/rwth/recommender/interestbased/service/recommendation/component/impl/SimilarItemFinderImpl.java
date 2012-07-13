/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.impl;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.service.recommendation.component.SimilarItemFinder;
import com.rwth.recommender.interestbased.service.recommendation.component.helper.CosineSimilarItemFinder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SimilarItemFinderImpl implements SimilarItemFinder{

    @Autowired
    private CosineSimilarItemFinder cosineSimilarItemFinder;
    
    @Override
    public List<ItemDTO> getXSimilarItems(List<ItemDTO> items, PersonDTO person, int numberOfItemsToReturn) {
	return cosineSimilarItemFinder.getXSimilarItems(items, person, numberOfItemsToReturn);
    }
    
}
