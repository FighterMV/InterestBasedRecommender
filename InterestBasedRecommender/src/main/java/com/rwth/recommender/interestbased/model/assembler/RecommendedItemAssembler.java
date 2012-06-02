/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.Item;
import com.rwth.recommender.interestbased.model.dto.RecommendedItemDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class RecommendedItemAssembler {
    
    public RecommendedItemDTO assembleDTO(Item item){
	RecommendedItemDTO recommendedItemDTO = new RecommendedItemDTO();
	recommendedItemDTO.setId(item.getId());
	recommendedItemDTO.setLink(item.getLink());
	recommendedItemDTO.setName(item.getName());
	return recommendedItemDTO;
    }
    
    public List<RecommendedItemDTO> assembleDTOList(List<Item> items){
	List<RecommendedItemDTO> itemDTOs = new ArrayList<RecommendedItemDTO>();
	for(Item item : items){
	    itemDTOs.add(assembleDTO(item));
	}
	return itemDTOs;
    }
    
    public Item assemble(RecommendedItemDTO recommendedItemDTO){
	Item item = new Item();
	item.setId(recommendedItemDTO.getId());
	item.setLink(recommendedItemDTO.getLink());
	item.setName(recommendedItemDTO.getName());
	return item;
    }
    
    public List<Item> assembleList(List<RecommendedItemDTO> recommendedItemDTOs){
	List<Item> items = new ArrayList<Item>();
	for(RecommendedItemDTO recommendedItemDTO : recommendedItemDTOs){
	    items.add(assemble(recommendedItemDTO));
	}
	return items;
    }
    
}
