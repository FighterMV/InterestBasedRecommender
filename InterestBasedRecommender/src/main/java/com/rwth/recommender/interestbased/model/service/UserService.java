/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service;

import com.rwth.recommender.interestbased.model.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface UserService {
    
    public List<UserDTO> getList();
    
    public void storeInDatabase(UserDTO userDTO);
    
}
