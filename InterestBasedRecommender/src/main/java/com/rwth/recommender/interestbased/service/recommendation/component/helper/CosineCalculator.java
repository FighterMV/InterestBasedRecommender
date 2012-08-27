/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation.component.helper;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface CosineCalculator {
      
    public double getInterestsAngle(List<PersonInterestDTO> interests1, List<PersonInterestDTO> interests2);
    
    public double getKeywordsAngle(List<String> keywords1, List<String> keywords2);
    
    	    
}