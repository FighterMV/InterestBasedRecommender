/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.recommendation.service.component;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface SimilarItemFinder {
    
    public List<ItemDTO> getXSimilarItems(List<ItemDTO> items, PersonDTO person, int numberOfItemsToReturn);
    
}
