package com.quiz.service;



import com.quiz.model.UserAuthRequest;


public interface AuthService {
	public boolean auth(UserAuthRequest userRequest);
}
