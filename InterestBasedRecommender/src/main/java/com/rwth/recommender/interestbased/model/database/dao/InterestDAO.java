/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao;

import com.rwth.recommender.interestbased.model.database.Interest;

/**
 *
 * @author Marco
 */
public interface InterestDAO {
    
    Interest get(Long id);
    void persist(Interest interest);
    
}