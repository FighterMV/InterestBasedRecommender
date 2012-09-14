/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api.service.impl;

import com.rwth.recommender.interestbased.api.service.SynonymService;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class SynonymServiceImpl implements SynonymService{

    @Override
    public List<String> getSynonyms(String word) {
	//!TODO Implement
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
