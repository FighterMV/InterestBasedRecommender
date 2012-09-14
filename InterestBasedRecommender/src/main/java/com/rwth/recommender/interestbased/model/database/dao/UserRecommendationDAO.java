/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao;

import com.rwth.recommender.interestbased.model.database.UserRecommendation;

/**
 *
 * @author Marco
 */
public interface UserRecommendationDAO {
    
    UserRecommendation get(long userId);
    void persist(UserRecommendation userRecommendation);
    
}
