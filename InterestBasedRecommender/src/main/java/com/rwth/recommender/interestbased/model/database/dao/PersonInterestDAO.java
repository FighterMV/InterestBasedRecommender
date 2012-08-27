/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.database.dao;

import com.rwth.recommender.interestbased.model.database.Person;
import com.rwth.recommender.interestbased.model.database.PersonInterest;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface PersonInterestDAO{

    public PersonInterest get(Long id);

    public void persist(PersonInterest item);
    
    public List<PersonInterest> getPersonInterests(Person person);

    public List<PersonInterest> getList();

    public void update(PersonInterest item);

    public void remove(PersonInterest item);

}
