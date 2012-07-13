/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface SimilarItemFinder {
    
    /**
     * Tis method calculates a specified number of items which are interesting for the provided person
     * @param items The list of items to be checked
     * @param person The person who needs a recommendation
     * @param numberOfItemsToReturn
     * @return A list of interesting items for the user
     */
    public List<ItemDTO> getXSimilarItems(List<ItemDTO> items, PersonDTO person, int numberOfItemsToReturn);
    
}
