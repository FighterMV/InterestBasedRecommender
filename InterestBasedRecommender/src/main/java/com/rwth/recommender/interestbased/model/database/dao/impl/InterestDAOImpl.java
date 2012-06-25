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
    
    @Override
    public Interest get(Long id) {
	return (Interest) sessionFactory.getCurrentSession().get(Interest.class, id);
    }

    @Override
    public void persist(Interest interest) {
	sessionFactory.getCurrentSession().persist(interest);
    }

    @Override
    public List<Interest> getInterests(Long personId) {
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Interest.class);
	criteria.add(Restrictions.eq("person.id", personId));
	return criteria.list();
    }
}
