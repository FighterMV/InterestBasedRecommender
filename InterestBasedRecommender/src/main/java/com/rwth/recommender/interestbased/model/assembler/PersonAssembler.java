/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.assembler;

import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class PersonAssembler {
    
    @Autowired
    private ItemAssembler itemAssembler;
    
    public PersonDTO assembleDTO(Person person){
	PersonDTO personDTO = new PersonDTO();
	personDTO.setId(person.getId());
	personDTO.setName(person.getName());
	List<String> personInterestKeywords = new ArrayList<String>();
	for(String interestKeyword : person.getPersonInterestKeywords()){
	    personInterestKeywords.add(interestKeyword);
	}
	personDTO.setPersonInterestKeywords(personInterestKeywords);
	personDTO.setProvidedItems(itemAssembler.assembleDTOList(person.getProvidedItems()));
	return personDTO;
    }
    
    public List<PersonDTO> assembleDTOList(List<Person> userList){
	List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
	for(Person person : userList){
	    PersonDTO personDTO = assembleDTO(person);
	    personDTOs.add(personDTO);
	}
	return personDTOs;
    }
    
    public Person assemble(PersonDTO personDTO){
	Person person = new Person();
	person.setId(personDTO.getId());
	person.setName(personDTO.getName());
	person.setPersonInterestKeywords(personDTO.getPersonInterestKeywords());
	person.setProvidedItems(itemAssembler.assembleList(personDTO.getProvidedItems()));
	return person;
    }
    
}
