/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.ItemRecommendation;
import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class ItemRecommendationAssembler {
    
    @Autowired
    private ItemAssembler recommendedItemAssembler;
    
    public ItemRecommendationDTO assembleDTO(ItemRecommendation itemRecommendation){
	ItemRecommendationDTO itemRecommendationDTO = new ItemRecommendationDTO();
	itemRecommendationDTO.setId(itemRecommendation.getId());
	itemRecommendationDTO.setAccuracy(itemRecommendation.getAccuracy());
	itemRecommendationDTO.setItem(recommendedItemAssembler.assembleDTO(itemRecommendation.getItem()));
	return itemRecommendationDTO;
    }
    
    public List<ItemRecommendationDTO> assembleDTOList(List<ItemRecommendation> itemRecommendations){
	List<ItemRecommendationDTO> itemRecommendationDTOs = new ArrayList<ItemRecommendationDTO>();
	for(ItemRecommendation itemRecommendation : itemRecommendations){
	    itemRecommendationDTOs.add(assembleDTO(itemRecommendation));
	}
	return itemRecommendationDTOs;
    }
    
    public ItemRecommendation assemble(ItemRecommendationDTO itemRecommendationDTO){
	ItemRecommendation itemRecommendation = new ItemRecommendation();
	itemRecommendation.setAccuracy(itemRecommendationDTO.getAccuracy());
	itemRecommendation.setId(itemRecommendationDTO.getId());
	itemRecommendation.setItem(recommendedItemAssembler.assemble(itemRecommendationDTO.getItem()));
	return itemRecommendation;
    }
    
    public List<ItemRecommendation> assembleList(List<ItemRecommendationDTO> itemRecommendationDTOs){
	List<ItemRecommendation> itemRecommendations = new ArrayList<ItemRecommendation>();
	for(ItemRecommendationDTO itemRecommendationDTO : itemRecommendationDTOs){
	    itemRecommendations.add(assemble(itemRecommendationDTO));
	}
	return itemRecommendations;
    }
    
}
