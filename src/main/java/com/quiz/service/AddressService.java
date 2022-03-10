package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dao.QuizDAO;
import com.quiz.model.StateModel;

@Service
public class AddressService {
	
	@Autowired
	private QuizDAO quizDAO;
	
	public List<String> getCityByState(String state) {
		List<String> cityList = quizDAO.getCityByState(state);
		
		return cityList;
		
	}

	public List<StateModel> getAllState() {
		List<StateModel> statelist = quizDAO.getAllState();
		return statelist;
	}
}
