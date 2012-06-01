/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

import com.rwth.recommender.interestbased.model.database.User;
import com.rwth.recommender.interestbased.model.database.dao.UserDAO;
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
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public User get(Long id) {
	return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void persist(User user) {
	sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public List<User> getList() {
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
	return criteria.list();
    }
    
}
