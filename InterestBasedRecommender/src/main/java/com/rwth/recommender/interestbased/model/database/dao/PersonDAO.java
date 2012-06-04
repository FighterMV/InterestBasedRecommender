/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao;

import com.rwth.recommender.interestbased.model.database.Person;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface PersonDAO {
    
    Person get(Long id);
    void persist(Person person);
    List<Person> getList();
    void update(Person person);
    
}
