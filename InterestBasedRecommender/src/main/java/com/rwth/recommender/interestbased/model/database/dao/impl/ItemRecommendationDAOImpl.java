/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao.impl;

import com.rwth.recommender.interestbased.model.database.ItemRecommendation;
import com.rwth.recommender.interestbased.model.database.dao.ItemRecommendationDAO;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco
 */
@Repository
public class ItemRecommendationDAOImpl implements ItemRecommendationDAO{

    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public void persist(ItemRecommendation itemRecommendation) {
	sessionFactory.getCurrentSession().persist(itemRecommendation);
    }
    
}
