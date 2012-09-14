/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao;

import com.rwth.recommender.interestbased.model.database.ItemRecommendation;
import java.util.Set;

/**
 *
 * @author Marco
 */
public interface ItemRecommendationDAO {
    
    public void persist(ItemRecommendation itemRecommendation);
        
}
