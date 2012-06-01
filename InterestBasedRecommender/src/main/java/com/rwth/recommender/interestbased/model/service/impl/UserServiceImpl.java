/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.service.UserService;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco
 */
@Component
public class UserServiceImpl implements UserService{

    @Override
    public List<UserDTO> getList() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
