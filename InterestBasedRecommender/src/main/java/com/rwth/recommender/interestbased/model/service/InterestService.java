/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface InterestService {
    
    public void storeInDatabase(InterestDTO interestDTO);
    
    public void storeInDatabase(List<InterestDTO>interestDTOs);
    
}
