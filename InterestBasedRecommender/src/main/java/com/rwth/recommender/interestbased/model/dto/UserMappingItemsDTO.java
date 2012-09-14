/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

import java.util.Set;

/**
 *
 * @author Marco
 */
public class UserMappingItemsDTO {
    
    Set<ItemDTO> mappingItems;
    
    Set<ItemDTO> otherItems;

    public Set<ItemDTO> getMappingItems() {
	return mappingItems;
    }

    public void setMappingItems(Set<ItemDTO> mappingItems) {
	this.mappingItems = mappingItems;
    }

    public Set<ItemDTO> getOtherItems() {
	return otherItems;
    }

    public void setOtherItems(Set<ItemDTO> otherItems) {
	this.otherItems = otherItems;
    }
    
    
    
}
