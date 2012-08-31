/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.Constants;
import com.rwth.recommender.interestbased.model.assembler.InterestAssembler;
import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.assembler.PersonInterestAssembler;
import com.rwth.recommender.interestbased.model.database.PersonInterest;
import com.rwth.recommender.interestbased.model.database.dao.InterestDAO;
import com.rwth.recommender.interestbased.model.database.dao.PersonInterestDAO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import com.rwth.recommender.interestbased.model.service.PersonInterestService;
import com.rwth.recommender.interestbased.service.recommendation.SimilarityService;
import java.util.ArrayList;
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
    
    @Autowired
    SimilarityService similarityService;
    
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

    @Override
    @Transactional
    public void findAndStoreSimilarInterests(PersonDTO personDTO) {
	
	List<PersonInterestDTO> similarPersonInterestDTOs = new ArrayList<PersonInterestDTO>();
	
	List<PersonInterest> currentPersonInterests = personInterestDAO.getPersonInterests(personAssembler.assemble(personDTO));
	List<PersonInterestDTO> currentPersonInterestDTOs = personInterestAssembler.assembleDTOList(currentPersonInterests);
	for(PersonInterestDTO personInterestDTO : currentPersonInterestDTOs){
	    similarPersonInterestDTOs.addAll(similarityService.findSimilarInterests(personInterestDTO));
	}
	
	normWeightings(similarPersonInterestDTOs);
		
	for(PersonInterestDTO personInterestDTO : similarPersonInterestDTOs){
	    if(personInterestDTO.getId() == null){
		Long interestId = interestDAO.persist(interestAssembler.assemble(personInterestDTO.getInterest()));
		personInterestDTO.getInterest().setId(interestId);
		PersonInterest personInterest = personInterestAssembler.assemble(personInterestDTO);
		personInterestDAO.persist(personInterest);
		personInterestDTO.setId(personInterest.getId());
	    }
	}
	
    }
    
    private void normWeightings(List<PersonInterestDTO> personInterestDTOs){
	int maxRating = 1;
	
	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    maxRating = Math.max(maxRating, personInterestDTO.getWeighting());
	}
	
	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    int oldRating = personInterestDTO.getWeighting();
	    double newRating = (new Double(oldRating) / new Double(maxRating)) * Constants.MAX_WEIGHTING;
	    personInterestDTO.setWeighting((int)newRating);
	}
    }
    
}
