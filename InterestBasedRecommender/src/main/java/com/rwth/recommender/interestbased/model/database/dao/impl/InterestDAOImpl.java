/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

import com.rwth.recommender.interestbased.model.database.Interest;
import com.rwth.recommender.interestbased.model.database.dao.InterestDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco
 */
@Repository
public class InterestDAOImpl implements InterestDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InterestDAOImpl.class);
    
    @Override
    public Interest get(Long id) {
	LOGGER.trace("Fetching interest with id: " + id + " from database");
	return (Interest) sessionFactory.getCurrentSession().get(Interest.class, id);
    }

    @Override
    public void persist(Interest interest) {
	LOGGER.trace("Persisting interest with name: " + interest.getName() + " in database");
	sessionFactory.getCurrentSession().persist(interest);
    }

    @Override
    public List<Interest> getInterests(Long personId) {
	LOGGER.trace("Fetching interests from person with id: " + personId + " from database");
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Interest.class);
	criteria.add(Restrictions.eq("person.id", personId));
	return criteria.list();
    }
}
