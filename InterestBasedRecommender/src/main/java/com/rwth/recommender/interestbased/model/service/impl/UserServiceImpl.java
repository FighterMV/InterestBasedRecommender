/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.model.service.impl;

import com.rwth.recommender.interestbased.model.assembler.UserAssembler;
import com.rwth.recommender.interestbased.model.database.User;
import com.rwth.recommender.interestbased.model.database.dao.UserDAO;
import com.rwth.recommender.interestbased.model.dto.UserDTO;
import com.rwth.recommender.interestbased.model.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marco
 */
@Component
public class UserServiceImpl implements UserService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDAO userDAO;
    
    @Autowired
    UserAssembler userAssembler;
    
    @Override
    @Transactional
    public List<UserDTO> getList() {
	LOGGER.debug("Getting userList from database");
	List<User> userList = userDAO.getList();
	List<UserDTO> userDTOs = userAssembler.assembleDTOList(userList);
	return userDTOs;
    }

    @Override
    @Transactional
    public void storeInDatabase(UserDTO userDTO) {
	LOGGER.debug("Storing user in database");
	User user = userAssembler.assemble(userDTO);
	userDAO.persist(user);
    }
    
}
