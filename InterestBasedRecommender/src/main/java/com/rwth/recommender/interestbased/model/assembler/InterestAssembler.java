/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.Interest;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class InterestAssembler {
    
    public InterestDTO assembleDTO(Interest interest){
	InterestDTO interestDTO = new InterestDTO();
	interestDTO.setName(interest.getName());
	return interestDTO;
    }
    
    public Interest assemble(InterestDTO interestDTO){
	Interest interest = new Interest();
	interest.setId(interestDTO.getId());
	interest.setName(interestDTO.getName());
	return interest;
    }
    
}
