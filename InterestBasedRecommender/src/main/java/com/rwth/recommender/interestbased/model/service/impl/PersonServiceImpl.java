/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.database.Item;
import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.dao.InterestDAO;
import com.rwth.recommender.interestbased.model.database.dao.ItemDAO;
import com.rwth.recommender.interestbased.model.database.dao.PersonDAO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.service.PersonService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class PersonServiceImpl implements PersonService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonDAO personDAO;
    
    @Autowired
    PersonAssembler personAssembler;
    
    @Autowired
    InterestDAO interestDAO;
    
    @Autowired
    ItemDAO itemDAO;
    
    @Override
    @Transactional
    public List<PersonDTO> getList() {
	LOGGER.debug("Getting userList from database");
	List<Person> personList = personDAO.getList();
	LOGGER.trace("Starting to assemble personDTOList");
	List<PersonDTO> personDTOs = personAssembler.assembleDTOList(personList);
	LOGGER.trace("Finished assembling personDTOList");
	return personDTOs;
    }

    @Override
    @Transactional
    public void storeInDatabase(PersonDTO personDTO) {
	LOGGER.debug("Storing person in database");
	Person person = personAssembler.assemble(personDTO);
	for(int i = 0; i < person.getProvidedItems().size(); i++){
	    Item item = person.getProvidedItems().get(i);
	    itemDAO.persist(item);
	    personDTO.getProvidedItems().get(i).setId(item.getId());
	}
	personDAO.persist(person);
	personDTO.setId(person.getId());
	
    }

    @Override
    @Transactional
    public void updatePersonInDatabase(PersonDTO personDTO) {
	Person person = personAssembler.assemble(personDTO);
	personDAO.update(person);
    }
    
}
