/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.Interest;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class InterestAssembler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InterestAssembler.class);
    
    @Autowired
    PersonAssembler personAssembler;
    
    public InterestDTO assembleDTO(Interest interest){
	InterestDTO interestDTO = new InterestDTO();
	interestDTO.setName(interest.getName());
	interestDTO.setId(interest.getId());
	return interestDTO;
    }
    
    public Interest assemble(InterestDTO interestDTO){
	Interest interest = new Interest();
	interest.setId(interestDTO.getId());
	interest.setName(interestDTO.getName());
	return interest;
    }
    
    public List<InterestDTO> assembleDTOList(List<Interest> interests){
	List<InterestDTO> interestDTOs = new ArrayList<InterestDTO>();
	for(Interest interest : interests){
	    InterestDTO interestDTO = assembleDTO(interest);
	    interestDTOs.add(interestDTO);
	}
	return interestDTOs;
    }
    
    public List<Interest> assembleList(List<InterestDTO> interestDTOs){
	List<Interest> interests = new ArrayList<Interest>();
	for(InterestDTO interestDTO : interestDTOs){
	    Interest interest = assemble(interestDTO);
	    interests.add(interest);
	}
	return interests;
    }
    
}
