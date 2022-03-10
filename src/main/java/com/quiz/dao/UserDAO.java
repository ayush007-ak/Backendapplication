package com.quiz.dao;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDAO {
	public UserDetails getUserDetails(String userName);
}
