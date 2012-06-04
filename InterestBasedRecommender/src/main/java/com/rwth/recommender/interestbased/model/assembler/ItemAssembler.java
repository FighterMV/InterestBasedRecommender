/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.Item;
import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class ItemAssembler {
    
    public ItemDTO assembleDTO(Item item){
	ItemDTO recommendedItemDTO = new ItemDTO();
	recommendedItemDTO.setId(item.getId());
	recommendedItemDTO.setLink(item.getLink());
	recommendedItemDTO.setName(item.getName());
	return recommendedItemDTO;
    }
    
    public Set<ItemDTO> assembleDTOSet(Set<Item> items){
	Set<ItemDTO> itemDTOs = new HashSet<ItemDTO>();
	for(Item item : items){
	    itemDTOs.add(assembleDTO(item));
	}
	return itemDTOs;
    }
    
    public Item assemble(ItemDTO recommendedItemDTO){
	Item item = new Item();
	item.setId(recommendedItemDTO.getId());
	item.setLink(recommendedItemDTO.getLink());
	item.setName(recommendedItemDTO.getName());
	return item;
    }
    
    public Set<Item> assembleSet(Set<ItemDTO> recommendedItemDTOs){
	Set<Item> items = new HashSet<Item>();
	for(ItemDTO recommendedItemDTO : recommendedItemDTOs){
	    items.add(assemble(recommendedItemDTO));
	}
	return items;
    }
    
}
