/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao;

import com.rwth.recommender.interestbased.model.database.User;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface UserDAO {
    
    User get(Long id);
    void persist(User user);
    List<User> getList();
    
}
