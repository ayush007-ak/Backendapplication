package com.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dao.QuizDAO;
import com.quiz.entity.User;
import com.quiz.model.UserAuthRequest;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private QuizDAO authUserDAO;

	

	@Override
	public boolean auth(UserAuthRequest userRequest) {
		
		User user = authUserDAO.authUser(userRequest.getUsername());
	    
		
	    
	    if(userRequest.getUsername().equals(user.getUserName()) && userRequest.getPassword().equals(user.getPassword())) {
	    	return true;
	    }
		return false;
	}




}
