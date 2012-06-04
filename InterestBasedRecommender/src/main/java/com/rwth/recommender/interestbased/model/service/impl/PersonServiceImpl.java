/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.PersonAssembler;
import com.rwth.recommender.interestbased.model.database.Interest;
import com.rwth.recommender.interestbased.model.database.ItemRecommendation;
import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.dao.InterestDAO;
import com.rwth.recommender.interestbased.model.database.dao.ItemDAO;
import com.rwth.recommender.interestbased.model.database.dao.ItemRecommendationDAO;
import com.rwth.recommender.interestbased.model.database.dao.PersonDAO;
import com.rwth.recommender.interestbased.model.dto.ItemRecommendationDTO;
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
    InterestDAO interestDAO;
    
    @Autowired
    ItemDAO itemDAO;
    
    @Autowired
    ItemRecommendationDAO itemRecommendationDAO;
    
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
	for(int i = 0; i < person.getItemRecommendations().size(); i++){
	    ItemRecommendation itemRecommendation = person.getItemRecommendations().get(i);
	    ItemRecommendationDTO itemRecommendationDTO = personDTO.getItemRecommendations().get(i);
	    itemDAO.persist(itemRecommendation.getItem());
	    itemRecommendationDTO.getItem().setId(itemRecommendation.getItem().getId());
	    itemRecommendationDAO.persist(itemRecommendation);
	    itemRecommendationDTO.setId(itemRecommendation.getId());
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
