/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.InterestAssembler;
import com.rwth.recommender.interestbased.model.database.Interest;
import com.rwth.recommender.interestbased.model.database.dao.InterestDAO;
import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.service.InterestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class InterestServiceImpl implements InterestService{

    @Autowired
    InterestDAO interestDAO;
    
    @Autowired
    InterestAssembler interestAssembler;
    
    @Override
    @Transactional
    public void storeInDatabase(InterestDTO interestDTO) {
	Interest interest = interestAssembler.assemble(interestDTO);
	interestDAO.persist(interest);
	interestDTO.setId(interest.getId());
    }

    @Override
    @Transactional
    public void storeInDatabase(List<InterestDTO> interestDTOs) {
	for(InterestDTO interestDTO : interestDTOs){
	    Interest interest = interestAssembler.assemble(interestDTO);
	    interestDAO.persist(interest);
	    interestDTO.setId(interest.getId());
	}
    }

    @Override
    @Transactional
    public List<InterestDTO> getInterests(PersonDTO person) {
	List<Interest> interests = interestDAO.getInterests(person.getId());
	return interestAssembler.assembleDTOList(interests);
    }
    
}
