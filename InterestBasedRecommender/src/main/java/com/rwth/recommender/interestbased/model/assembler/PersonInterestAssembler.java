/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.PersonInterest;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class PersonInterestAssembler {
    
    @Autowired
    InterestAssembler interestAssembler;
    
    @Autowired
    PersonAssembler personAssembler;
    
    public PersonInterest assemble(PersonInterestDTO personInterestDTO){
	PersonInterest personInterest = new PersonInterest();

	personInterest.setId(personInterestDTO.getId());
	personInterest.setInterest(interestAssembler.assemble(personInterestDTO.getInterest()));
	personInterest.setPerson(personAssembler.assemble(personInterestDTO.getPerson()));
	personInterest.setWeighting(personInterestDTO.getWeighting());

	return personInterest;
    }
    
    public PersonInterestDTO assembleDTO(PersonInterest personInterest){
	PersonInterestDTO personInterestDTO = new PersonInterestDTO();
	
	personInterestDTO.setId(personInterest.getId());
	personInterestDTO.setInterest(interestAssembler.assembleDTO(personInterest.getInterest()));
	personInterestDTO.setPerson(personAssembler.assembleDTO(personInterest.getPerson()));
	personInterestDTO.setWeighting(personInterest.getWeighting());

	return personInterestDTO;
    }
    
    public List<PersonInterest> assembleList(List<PersonInterestDTO> personInterestDTOs){
	List<PersonInterest> personInterests = new ArrayList<PersonInterest>();

	for(PersonInterestDTO personInterestDTO : personInterestDTOs){
	    PersonInterest personInterest = assemble(personInterestDTO);
	    personInterests.add(personInterest); 
	}

	return personInterests;
    }
    
    public List<PersonInterestDTO> assembleDTOList(List<PersonInterest> personInterests){
	List<PersonInterestDTO> personInterestDTOs = new ArrayList<PersonInterestDTO>();

	for(PersonInterest personInterest : personInterests){
	    PersonInterestDTO personInterestDTO = assembleDTO(personInterest);
	    personInterestDTOs.add(personInterestDTO); 
	}

	return personInterestDTOs;
    }
    
}
