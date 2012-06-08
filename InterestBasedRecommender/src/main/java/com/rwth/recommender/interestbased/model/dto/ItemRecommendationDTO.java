/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.dto;

/**
 *
 * @author Marco
 */
public class ItemRecommendationDTO {
    
    private ItemDTO item;
    private int accuracy;

    public int getAccuracy() {
	return accuracy;
    }

    public ItemDTO getItem() {
	return item;
    }

    public void setAccuracy(int accuracy) {
	this.accuracy = accuracy;
    }

    public void setItem(ItemDTO recommendedItem) {
	this.item = recommendedItem;
    }
           
}
