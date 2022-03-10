package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entity.User;
import com.quiz.model.QuizResponse;
import com.quiz.model.ScoreRequest;
import com.quiz.model.ScoreResponse;
import com.quiz.model.UserResponse;
import com.quiz.service.QuizService;

@RestController
@RequestMapping("private")

@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true",allowedHeaders = "*")
@EnableAspectJAutoProxy
public class QuizController {
	
	@Autowired
	private QuizService quizservice;
	
	
	//@RequestMapping(value="employees",method = RequestMethod.GET)
	@GetMapping("quizdata/{quizSubject}")
	public ResponseEntity<List<QuizResponse>> GetAllEmployees(@PathVariable("quizSubject")String quizSubject){
		List<QuizResponse> quizResponseList=quizservice.quizesRetrievalServices(quizSubject);
		ResponseEntity<List<QuizResponse>> response=null;
		if(!quizResponseList.isEmpty()) {
		 response=new ResponseEntity<List<QuizResponse>>(quizResponseList,HttpStatus.OK);
		}else {
			response=new ResponseEntity<List<QuizResponse>>(quizResponseList,HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
		
	
	
	@GetMapping("score/{username}")
	public ResponseEntity<ScoreResponse> getScoreByUsername(@PathVariable("username")String username){
		ScoreResponse scoreResponse = new ScoreResponse();
		scoreResponse = quizservice.getScoreByUsername(username);
		ResponseEntity<ScoreResponse> response = null;
		
		if(scoreResponse.equals(null)) {
			response=new ResponseEntity<ScoreResponse>(HttpStatus.NOT_FOUND);
		}else {
			response =new ResponseEntity<ScoreResponse>(scoreResponse,HttpStatus.FOUND);
		}
		
		return response;
		
	}
	
	@GetMapping("user/{username}")
	public ResponseEntity<UserResponse> getUserDetails(@PathVariable("username")String username){
		User user = quizservice.getUserDetails(username);
		ResponseEntity<UserResponse> response = null;
		UserResponse userResponse = new UserResponse();
		userResponse.setUserName(user.getUserName());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setDob(user.getDob());
		userResponse.setCity(user.getCity());
		userResponse.setAddress(user.getAddress());
		userResponse.setState(user.getState());
		userResponse.setEmailId(user.getEmailId());
		userResponse.setGender(user.getGender());
		
		if(userResponse.equals(null)) {
			response=new ResponseEntity<UserResponse>(HttpStatus.NOT_FOUND);
		}else {
			response =new ResponseEntity<UserResponse>(userResponse,HttpStatus.FOUND);
		}
		
		
		return response;
		
	}
	
	
}
