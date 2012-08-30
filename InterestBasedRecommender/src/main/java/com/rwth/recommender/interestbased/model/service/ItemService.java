/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service;

import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface ItemService {
    
    public void storeItemInDatabase(ItemDTO itemDTO);
    
    public void storeItemsInDatabase(List<ItemDTO> itemDTOs);
    
}
