/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.ItemRecommendationAssembler;
import com.rwth.recommender.interestbased.model.database.ItemRecommendation;
import com.rwth.recommender.interestbased.model.database.dao.ItemDAO;
import com.rwth.recommender.interestbased.model.database.dao.ItemRecommendationDAO;
import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import com.rwth.recommender.interestbased.model.service.ItemRecommendationService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class ItemRecommendationServiceImpl implements ItemRecommendationService{

    @Autowired
    ItemRecommendationDAO itemRecommendationDAO;
    
    @Autowired
    ItemDAO itemDAO;
    
    @Autowired
    ItemRecommendationAssembler itemRecommendationAssembler;
    
    @Override
    @Transactional
    public void storeInDatabase(List<ItemRecommendationDTO> itemRecommendations) {
	List<ItemRecommendation> itemRecommendationsToBeStored = itemRecommendationAssembler.assembleList(itemRecommendations);
	for(ItemRecommendation itemRecommendationToBeStored : itemRecommendationsToBeStored){
	    itemDAO.persist(itemRecommendationToBeStored.getItem());
	    itemRecommendationDAO.persist(itemRecommendationToBeStored);
	}
    }
    
}
