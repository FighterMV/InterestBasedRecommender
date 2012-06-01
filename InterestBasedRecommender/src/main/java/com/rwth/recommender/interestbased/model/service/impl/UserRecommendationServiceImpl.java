/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.database.dao.UserRecommendationDAO;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;
import com.rwth.recommender.interestbased.model.service.UserRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class UserRecommendationServiceImpl implements UserRecommendationService{

    @Autowired
    private UserRecommendationDAO userRecommendationDAO;

    
    @Override
    @Transactional
    public UserRecommendationDTO getRecommendationForUser(Long userId) {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
