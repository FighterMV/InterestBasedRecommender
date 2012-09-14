/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.UserRecommendationAssembler;
import com.rwth.recommender.interestbased.model.database.UserRecommendation;
import com.rwth.recommender.interestbased.model.database.dao.UserRecommendationDAO;
import com.rwth.recommender.interestbased.model.dto.UserRecommendationDTO;
import com.rwth.recommender.interestbased.model.service.UserRecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class UserRecommendationServiceImpl implements UserRecommendationService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRecommendationServiceImpl.class);
    
    @Autowired
    private UserRecommendationDAO userRecommendationDAO;
    
    @Autowired
    private UserRecommendationAssembler userRecommendationAssembler;

    
    @Override
    @Transactional
    public UserRecommendationDTO getRecommendationForUser(Long userId) {
	LOGGER.debug("Getting UserRecommendation for user with id " + userId);
	UserRecommendation userRecommendation = userRecommendationDAO.get(userId);
	UserRecommendationDTO userRecommendationDTO = userRecommendationAssembler.assembleDTO(userRecommendation);
	return userRecommendationDTO;
    }

    @Override
    @Transactional
    public void storeInDatabase(UserRecommendationDTO userRecommendationDTO) {
	LOGGER.debug("Storing UserRecommendation");
	UserRecommendation userRecommendation = userRecommendationAssembler.assemble(userRecommendationDTO);
	userRecommendationDAO.persist(userRecommendation);
	
    }
    
}
