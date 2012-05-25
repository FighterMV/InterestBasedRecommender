/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api.service;

import java.util.List;

/**
 *
 * @author Marco
 */
public interface SynonymService {
    
    public List<String> getSynonyms(String word);
    
}
