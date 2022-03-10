package com.quiz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quiz.dao.UserDAO;
@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails userDetails=null;
		try {
			userDetails=userDAO.getUserDetails(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

}
