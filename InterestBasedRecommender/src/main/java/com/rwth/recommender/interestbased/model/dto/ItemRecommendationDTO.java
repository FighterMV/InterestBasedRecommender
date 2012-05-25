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
    
    private RecommendedItemDTO recommendedItem;
    private int accuracy;

    public int getAccuracy() {
	return accuracy;
    }

    public RecommendedItemDTO getRecommendedItem() {
	return recommendedItem;
    }
        
}
