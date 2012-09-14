/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

import com.rwth.recommender.interestbased.model.database.UserRecommendation;
import com.rwth.recommender.interestbased.model.database.dao.UserRecommendationDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco
 */
@Repository
public class UserRecommendationDAOImpl implements UserRecommendationDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public UserRecommendation get(long userId) {
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserRecommendation.class);
	Criterion criterion = Restrictions.ilike("user.id", userId);
	criteria.add(criterion);
	return (UserRecommendation) criteria.uniqueResult();
    }

    @Override
    public void persist(UserRecommendation userRecommendation) {
	sessionFactory.getCurrentSession().persist(userRecommendation);
    }
    
}
