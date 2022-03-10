package com.quiz.controller;

import java.util.ArrayList;
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

import com.quiz.model.ScoreRequest;
import com.quiz.model.StateModel;
import com.quiz.model.UserAuthRequest;
import com.quiz.model.UserRequest;
import com.quiz.model.UserResponse;
import com.quiz.service.AddressService;
import com.quiz.service.AuthService;
import com.quiz.service.QuizService;

@RestController
@RequestMapping("public")

@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true",allowedHeaders = "*")
@EnableAspectJAutoProxy
public class UserController {
		
	@Autowired
	private QuizService quizservice;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AddressService addressService;

	@PostMapping("users")
	public ResponseEntity<UserResponse> persistUser(@RequestBody UserRequest userRequest){
		boolean result=quizservice.persistUser(userRequest);
		ResponseEntity<UserResponse> response=null;
		if(result) {
			UserResponse userResponse = new UserResponse();
			userResponse.setFirstName(userRequest.getFirstName());
			userResponse.setLastName(userRequest.getLastName());
			userResponse.setUserName(userRequest.getUserName());
			userResponse.setDob(userRequest.getDob());
			userResponse.setState(userRequest.getState());
			userResponse.setCity(userRequest.getCity());
			userResponse.setGender(userRequest.getGender());
            userResponse.setEmailId(userRequest.getEmailId());
            userResponse.setAddress(userRequest.getAddress());
			response=new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
		}else {
			response=new ResponseEntity<UserResponse>(HttpStatus.CONFLICT);

		}
		return response;
		
	}
	
	@PostMapping("login")
	public Boolean authUser(@RequestBody UserAuthRequest userRequest){
		boolean isAuthentication = authService.auth(userRequest);
		
		
		if(isAuthentication)
			return true;
		else
			return false;
	}
	
	@GetMapping("{state}")
	public List<String> getAllCityByState(@PathVariable("state")String state){
		
	
		
		List<String> cityList = new ArrayList<String>();
		cityList = addressService.getCityByState(state);
		
		return cityList;
	}
	
	@GetMapping("state")
	public List<StateModel> getAllState(){
		
		List<StateModel> statelist = addressService.getAllState();
		
		return statelist;
	}
	
	
	@PostMapping("score")
	public boolean saveScore(@RequestBody ScoreRequest scoreRequest){
		return quizservice.saveScore(scoreRequest);
		
	}
	
	
}
