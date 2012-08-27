/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service;

import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface PersonInterestService {
    
    public List<PersonInterestDTO> getPersonInterests(PersonDTO person);
    
    public void storeInDatabase(List<PersonInterestDTO> personInterests);
    
}
