package com.quiz.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dao.QuizDAO;
import com.quiz.entity.Quiz;
import com.quiz.entity.User;
import com.quiz.model.QuizResponse;

import com.quiz.model.ScoreRequest;
import com.quiz.model.ScoreResponse;
import com.quiz.model.UserRequest;
@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizDAO quizdao;

	@Override
	public List<QuizResponse> quizesRetrievalServices(String quizSubject) {
		List<Quiz> quizList = quizdao.getQuiz(quizSubject);
		List <QuizResponse> quizResponseList = new ArrayList<QuizResponse>();
		for(Quiz quiz:quizList) {
			QuizResponse quizResponse = new QuizResponse();
			quizResponse.setQID(quiz.getQID());
			quizResponse.setQuestions(quiz.getQuestions());
			quizResponse.setOption1(quiz.getOption1());
			quizResponse.setOption2(quiz.getOption2());
			quizResponse.setOption3(quiz.getOption3());
			quizResponse.setOption4(quiz.getOption4());
			quizResponse.setCorrectAnswer(quiz.getCorrectAnswer());
			quizResponseList.add(quizResponse);

	}
		return quizResponseList;
	}


	@Override
	public boolean persistUser(UserRequest userRequest) {
	   User user = new User();
	   user.setFirstName(userRequest.getFirstName());
	   user.setLastName(userRequest.getLastName());
	   user.setUserName(userRequest.getUserName());
	   user.setEmailId(userRequest.getEmailId());
	   user.setPassword(userRequest.getPassword());
	   user.setAddress(userRequest.getAddress());
	   user.setCity(userRequest.getCity());
	   user.setState(userRequest.getState());
	   user.setGender(userRequest.getGender());
	   user.setDob(userRequest.getDob());
		return quizdao.persistQuiz(user);
	}

	@Override
	public boolean saveScore(ScoreRequest scoreRequest) {
		return quizdao.saveScore(scoreRequest);
		
	}


	@Override
	public ScoreResponse getScoreByUsername(String username) {
		ScoreResponse scoreResponse = quizdao.getScoreByUsername(username);
		return scoreResponse;
	}


	@Override
	public User getUserDetails(String username) {
		User user = quizdao.getUserDetails(username);
		return user;
	}

	
}
