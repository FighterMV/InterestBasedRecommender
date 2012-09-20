/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.InterestAssembler;
import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.database.Item;
import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.dao.InterestDAO;
import com.rwth.recommender.interestbased.model.database.dao.ItemDAO;
import com.rwth.recommender.interestbased.model.database.dao.PersonDAO;
import com.rwth.recommender.interestbased.model.database.dao.PersonInterestDAO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.service.PersonService;
import java.util.ArrayList;
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
    InterestAssembler interestAssembler;
    
    @Autowired
    PersonInterestDAO personInterestDAO;
    
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
	personDAO.persist(person);
	personDTO.setId(person.getId());
	
    }

    @Override
    @Transactional
    public void updatePersonInDatabase(PersonDTO personDTO, List<Long> providedItems) {
	Person person = personDAO.get(personDTO.getId());
	person.setProvidedItems(new ArrayList<Item>());
	if(providedItems != null && providedItems.size() > 0){
	    for(Long itemId : providedItems){
		Item providedItem = itemDAO.get(itemId);
		person.getProvidedItems().add(providedItem);
	    }
	}
	personDAO.update(person);
    }
    
    
}
