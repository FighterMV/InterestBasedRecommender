/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.Item;
import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class ItemAssembler {
    
    public ItemDTO assembleDTO(Item item){
	ItemDTO itemDTO = new ItemDTO();
	itemDTO.setId(item.getId());
	itemDTO.setLink(item.getLink());
	itemDTO.setName(item.getName());
	List<String> describingKeywords = new ArrayList<String>();
	for(String describingKeyword : item.getDescribingKeywords()){
	    describingKeywords.add(describingKeyword);
	}
	itemDTO.setDescribingKeywords(describingKeywords);
	return itemDTO;
    }
    
    public List<ItemDTO> assembleDTOList(List<Item> items){
	List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
	for(Item item : items){
	    itemDTOs.add(assembleDTO(item));
	}
	return itemDTOs;
    }
    
    public Item assemble(ItemDTO itemDTO){
	Item item = new Item();
	item.setId(itemDTO.getId());
	item.setLink(itemDTO.getLink());
	item.setName(itemDTO.getName());
	item.setDescribingKeywords(itemDTO.getDescribingKeywords());
	return item;
    }
    
    public List<Item> assembleList(List<ItemDTO> recommendedItemDTOs){
	List<Item> items = new ArrayList<Item>();
	for(ItemDTO recommendedItemDTO : recommendedItemDTOs){
	    items.add(assemble(recommendedItemDTO));
	}
	return items;
    }
    
}
