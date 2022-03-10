package com.quiz.service;

import java.util.List;

import com.quiz.entity.User;
import com.quiz.model.QuizResponse;

import com.quiz.model.ScoreRequest;
import com.quiz.model.ScoreResponse;

import com.quiz.model.UserRequest;



public interface QuizService {
	

	public List<QuizResponse> quizesRetrievalServices(String quizSubject);
	public boolean persistUser(UserRequest userRequest);
	public boolean saveScore(ScoreRequest scoreRequest);
	public ScoreResponse getScoreByUsername(String username);
	public User getUserDetails(String username);


}
