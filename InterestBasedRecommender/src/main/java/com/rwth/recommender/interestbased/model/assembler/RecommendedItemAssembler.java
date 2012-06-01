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
    
    public RecommendedItemDTO getDTO(Item item){
	RecommendedItemDTO recommendedItemDTO = new RecommendedItemDTO();
	recommendedItemDTO.setLink(item.getLink());
	recommendedItemDTO.setName(item.getName());
	return recommendedItemDTO;
    }
    
    public List<RecommendedItemDTO> getDTOList(List<Item> items){
	List<RecommendedItemDTO> itemDTOs = new ArrayList<RecommendedItemDTO>();
	for(Item item : items){
	    itemDTOs.add(getDTO(item));
	}
	return itemDTOs;
    }
    
}
