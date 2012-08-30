/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao;

import com.rwth.recommender.interestbased.model.database.Item;

/**
 *
 * @author Marco
 */
public interface ItemDAO {
    
    Boolean persistAndReturnIfExisted(Item item);
    Item get(Long id);
    
}
