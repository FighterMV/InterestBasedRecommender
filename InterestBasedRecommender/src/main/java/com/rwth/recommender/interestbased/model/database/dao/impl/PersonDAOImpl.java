/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.dao.PersonDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco
 */
@Repository
public class PersonDAOImpl implements PersonDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Person get(Long id) {
	return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public void persist(Person person) {
	sessionFactory.getCurrentSession().persist(person);
    }

    @Override
    public List<Person> getList() {
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
	return criteria.list();
    }

    @Override
    public void update(Person person) {
	sessionFactory.getCurrentSession().update(person);
    }
    
}
