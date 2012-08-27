/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.InterestAssembler;
import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.assembler.PersonInterestAssembler;
import com.rwth.recommender.interestbased.model.database.PersonInterest;
import com.rwth.recommender.interestbased.model.database.dao.InterestDAO;
import com.rwth.recommender.interestbased.model.database.dao.PersonInterestDAO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.service.InterestService;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class PersonInterestServiceImpl implements PersonInterestService{

    @Autowired
    PersonInterestDAO personInterestDAO;
    
    @Autowired
    InterestDAO interestDAO;
    
    @Autowired
    PersonInterestAssembler personInterestAssembler;
    
    @Autowired
    PersonAssembler personAssembler;
    
    @Autowired
    InterestAssembler interestAssembler;
    
    @Override
    @Transactional
    public List<PersonInterestDTO> getPersonInterests(PersonDTO person) {
	List<PersonInterest> personInterests = personInterestDAO.getPersonInterests(personAssembler.assemble(person));
	return personInterestAssembler.assembleDTOList(personInterests);
    }

    @Override
    @Transactional
    public void storeInDatabase(List<PersonInterestDTO> personInterests) {
	for(PersonInterestDTO personInterestDTO : personInterests){
	    Long interestId = interestDAO.persist(interestAssembler.assemble(personInterestDTO.getInterest()));
	    personInterestDTO.getInterest().setId(interestId);
	    PersonInterest personInterest = personInterestAssembler.assemble(personInterestDTO);
	    personInterestDAO.persist(personInterest);
	    personInterestDTO.setId(personInterest.getId());
	}
    }
    
}