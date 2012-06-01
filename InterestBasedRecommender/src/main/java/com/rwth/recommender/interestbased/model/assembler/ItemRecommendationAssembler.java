/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.ItemRecommendation;
import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class ItemRecommendationAssembler {
    
    @Autowired
    private RecommendedItemAssembler recommendedItemAssembler;
    
    public ItemRecommendationDTO getDTO(ItemRecommendation itemRecommendation){
	ItemRecommendationDTO itemRecommendationDTO = new ItemRecommendationDTO();
	itemRecommendationDTO.setAccuracy(itemRecommendation.getAccuracy());
	itemRecommendationDTO.setRecommendedItem(recommendedItemAssembler.getDTO(itemRecommendation.getItem()));
	return itemRecommendationDTO;
    }
    
    public List<ItemRecommendationDTO> getDTOList(List<ItemRecommendation> itemRecommendations){
	List<ItemRecommendationDTO> itemRecommendationDTOs = new ArrayList<ItemRecommendationDTO>();
	for(ItemRecommendation itemRecommendation : itemRecommendations){
	    itemRecommendationDTOs.add(getDTO(itemRecommendation));
	}
	return itemRecommendationDTOs;
    }
    
}
