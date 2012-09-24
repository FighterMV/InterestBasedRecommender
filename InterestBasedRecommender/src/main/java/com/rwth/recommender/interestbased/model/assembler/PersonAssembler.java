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
public class PersonAssembler {
    
    @Autowired
    private ItemAssembler itemAssembler;
    
    @Autowired
    private PersonInterestAssembler personInterestAssembler;
    
    /**
     * PERSONINTERESTS ARE NOT ASSEMBLED
     * @param person
     * @return 
     */
    public PersonDTO assembleDTO(Person person){
	PersonDTO personDTO = new PersonDTO();
	personDTO.setId(person.getId());
	personDTO.setName(person.getName());
	personDTO.setLink(person.getLink());
	
	List<String> personMainTopics = new ArrayList<String>();
	for(String mainTopicKeyword : person.getPersonMainTopics()){
	    personMainTopics.add(mainTopicKeyword);
	}
	personDTO.setPersonMainTopics(personMainTopics);
	
	personDTO.setProvidedItems(itemAssembler.assembleDTOList(person.getProvidedItems()));
	
	return personDTO;
    }
    
    /**
     * PERSONINTERESTS ARE NOT ASSEMBLED
     * @param person
     * @return 
     */
    public List<PersonDTO> assembleDTOList(List<Person> userList){
	List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
	for(Person person : userList){
	    PersonDTO personDTO = assembleDTO(person);
	    personDTOs.add(personDTO);
	}
	return personDTOs;
    }
    
    /**
     * PERSONINTERESTS ARE NOT ASSEMBLED
     * @param person
     * @return 
     */
    public Person assemble(PersonDTO personDTO){
	Person person = new Person();
	person.setId(personDTO.getId());
	person.setName(personDTO.getName());
	person.setLink(personDTO.getLink());
	person.setPersonMainTopics(personDTO.getPersonMainTopics());
	person.setProvidedItems(itemAssembler.assembleList(personDTO.getProvidedItems()));
	return person;
    }
    
}
