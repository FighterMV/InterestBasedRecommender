/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.service.recommendation;

import com.rwth.recommender.interestbased.model.dto.InterestDTO;
import com.rwth.recommender.interestbased.model.dto.ItemDTO;
import com.rwth.recommender.interestbased.model.dto.PersonDTO;
import com.rwth.recommender.interestbased.model.dto.PersonInterestDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface SimilarityService {
    
    /**
     * This method receives a keyword and returns a list of similar words
     * @param keyword
     * @return A list of similar keywords
     */
    public List<PersonInterestDTO> findSimilarInterests(PersonInterestDTO keyword);
        
    /**
     * This method receives a person and returns a List of similar persons
     * @param person
     * @return A list of similar persons
     */
    public List<PersonDTO> findSimilarPersons(PersonDTO person);
    
    /**
     * This method receives a list of items and a person and returns a List which contains items the person may like
     * @param items The items to be checked
     * @param person
     * @return A subset of the provided items which are interesting for the person
     */
    public List<ItemDTO> findSimilarItems(List<ItemDTO> items, PersonDTO person);
    
    /**
     * This method returns a list of interested keywords for a raw list of weighted interests
     * @param weightedInterests
     * @return 
     */
    public List<PersonInterestDTO> getSimilarInterests(List<PersonInterestDTO> weightedInterests);
    
}
