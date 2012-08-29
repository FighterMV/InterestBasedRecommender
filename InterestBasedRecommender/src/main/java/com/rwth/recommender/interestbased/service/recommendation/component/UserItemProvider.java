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
public interface UserItemProvider {
    
    public List<ItemDTO> getItemsOfUserFittingForUser(PersonDTO userToBeRecommended, PersonDTO similarUser);
    
}
