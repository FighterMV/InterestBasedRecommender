/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

/**
 *
 * @author Marco
 */
import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.PersonInterest;
import com.rwth.recommender.interestbased.model.database.dao.PersonInterestDAO;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Repository
public class PersonInterestDAOImpl implements PersonInterestDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PersonInterest get(Long id){
	return (PersonInterest) sessionFactory.getCurrentSession().get(PersonInterest.class, id);
    }

    @Override
    public void persist(PersonInterest item){
	sessionFactory.getCurrentSession().persist(item);
    }

    @Override
    public List<PersonInterest> getList(){
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PersonInterest.class);
	return criteria.list();	
    }

    @Override
    public void update(PersonInterest item){
	sessionFactory.getCurrentSession().update(item);
    }

    @Override
    public void remove(PersonInterest item){
	sessionFactory.getCurrentSession().delete(item);
    }

    @Override
    public List<PersonInterest> getPersonInterests(Person person) {
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PersonInterest.class);
	criteria.add(Restrictions.eq("person.id", person.getId()));
	return criteria.list();
    }

}
