/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.ItemAssembler;
import com.rwth.recommender.interestbased.model.database.Item;
import com.rwth.recommender.interestbased.model.database.dao.ItemDAO;
import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemAssembler itemAssembler;
    
    @Autowired
    ItemDAO itemDAO;
    
    @Override
    @Transactional
    public void storeItemInDatabase(ItemDTO itemDTO) {
	Item item = itemAssembler.assemble(itemDTO);
	Boolean alreadyExisted = itemDAO.persistAndReturnIfExisted(item);
	if(alreadyExisted){
	    itemDTO.setDescribingKeywords(item.getDescribingKeywords());
	    itemDTO.setLink(item.getLink());
	    itemDTO.setName(item.getName());
	}
	itemDTO.setId(item.getId());
    }

    @Override
    @Transactional
    public void storeItemsInDatabase(List<ItemDTO> itemDTOs) {
	List<Item> items = itemAssembler.assembleList(itemDTOs);
	for(int i = 0; i < items.size(); i++){
	    Item item = items.get(i);
	    Boolean alreadyExistied = itemDAO.persistAndReturnIfExisted(item);
	    ItemDTO itemDTO = itemDTOs.get(i);
	    if(alreadyExistied){
		itemDTO.setDescribingKeywords(new ArrayList<String>());
		for(String describingKeyword : item.getDescribingKeywords()){
		    itemDTO.getDescribingKeywords().add(describingKeyword);
		}
		itemDTO.setLink(item.getLink());
		itemDTO.setName(item.getName());
	    }
	    itemDTO.setId(item.getId());
	    
	}
    }
    
}
