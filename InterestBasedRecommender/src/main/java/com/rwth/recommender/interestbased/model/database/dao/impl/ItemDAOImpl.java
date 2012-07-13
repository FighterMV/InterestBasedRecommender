/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

import com.rwth.recommender.interestbased.model.database.Item;
import com.rwth.recommender.interestbased.model.database.dao.ItemDAO;
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
public class ItemDAOImpl implements ItemDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void persist(Item item) {
	LOGGER.trace("Persisting item with name: " + item.getName() + " in database");
	sessionFactory.getCurrentSession().persist(item);
    }

    @Override
    public Item get(Long id) {
	LOGGER.trace("Fetching item with id: " + id + " from database");
	return (Item) sessionFactory.getCurrentSession().get(Item.class, id);
    }
    
}
