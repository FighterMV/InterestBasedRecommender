/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface CosineCalculator {
      
    public double getInterestsAngle(List<InterestDTO> interests1, List<InterestDTO> interests2);
    
    public double getKeywordsAngle(List<String> keywords1, List<String> keywords2);
    
    	    
}