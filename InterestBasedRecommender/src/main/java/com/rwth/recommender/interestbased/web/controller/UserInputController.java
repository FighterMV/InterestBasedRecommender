/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.web.controller;

import com.rwth.recommender.interestbased.web.model.StoreAndRecommendUserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marco
 */
@Controller
public class UserInputController {
    
    @RequestMapping("/user-input")
    public ModelAndView showUserInput(){
	ModelAndView modelAndView = new ModelAndView("userInputForm");
	StoreAndRecommendUserModel storeAndRecommendUserModel = new StoreAndRecommendUserModel();
	modelAndView.addObject("storeAndRecommendUserModel", storeAndRecommendUserModel);
	return modelAndView;
    }
    
}
