/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.dao.PersonDAO;
import com.rwth.recommender.interestbased.model.database.dao.PersonInterestDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco
 */
@Repository
public class PersonDAOImpl implements PersonDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    PersonInterestDAO personInterestDAO;
    
    @Override
    public Person get(Long id) {
	LOGGER.trace("Fetching person with id: " + id + " from database");
	return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public void persist(Person person) {
	LOGGER.trace("Persisting person with name: " + person.getName() + " from database");
	sessionFactory.getCurrentSession().persist(person);
    }

    @Override
    public List<Person> getList() {
	LOGGER.trace("Fetching list of all persons from database");
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
	return criteria.list();
    }

    @Override
    public void update(Person person) {
	LOGGER.trace("Updating person with id: " + person.getId() + " in database");
	sessionFactory.getCurrentSession().update(person);
    }
    
}
