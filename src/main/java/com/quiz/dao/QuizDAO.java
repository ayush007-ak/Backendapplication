package com.quiz.dao;

import java.util.List;

import com.quiz.entity.Quiz;
import com.quiz.entity.User;
import com.quiz.model.ScoreRequest;
import com.quiz.model.ScoreResponse;
import com.quiz.model.StateModel;

public interface QuizDAO {

	public List<Quiz> getQuiz(String quizSubject);
	public boolean persistQuiz(User user);
	public User authUser(String userName);
	public List<String> getCityByState(String state);
	public List<StateModel> getAllState();
	public boolean saveScore(ScoreRequest scoreRequest);
	public ScoreResponse getScoreByUsername(String username);
	public User getUserDetails(String username);
}
