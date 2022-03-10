package com.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.entity.Quiz;
import com.quiz.entity.User;
import com.quiz.helper.QuizDBQuery;
import com.quiz.integrate.ConnectionManager;
import com.quiz.model.ScoreRequest;
import com.quiz.model.ScoreResponse;
import com.quiz.model.StateModel;


@Repository("JDBCQuizDAOImpl")
public class JDBCQuizDAOImpl implements QuizDAO {
	
	@Autowired
	private ConnectionManager connectionManager;
	@Autowired
	private QuizDBQuery quizdbquery;
	

	@Override
	public List<Quiz> getQuiz(String quizSubject) {
		Connection connection=connectionManager.openConnection();
		List<Quiz> quizList=new ArrayList<Quiz>();
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet = null;
			
			if(quizSubject.equals("cssquestions")) {
				resultSet=statement.executeQuery(quizdbquery.getSelectDataQuerycssquestions());
			}
			
			if(quizSubject.equals("javaquestions")) {
				resultSet=statement.executeQuery(quizdbquery.getSelectDataQueryjavaquestions());
			}
			
			if(quizSubject.equals("databasequestions")) {
				resultSet=statement.executeQuery(quizdbquery.getSelectDataQuerydatabasequestions());
			}
			
			if(quizSubject.equals("pythonquestions")) {
				resultSet=statement.executeQuery(quizdbquery.getSelectDataQuerypythonquestions());
			}
			
			
			while(resultSet.next()) {
				Quiz q=new Quiz();
				q.setQID(resultSet.getInt(1));
				q.setQuestions(resultSet.getString(2));
				q.setOption1(resultSet.getString(3));
				q.setOption2(resultSet.getString(4));
				q.setOption3(resultSet.getString(5));
				q.setOption4(resultSet.getString(6));
				q.setCorrectAnswer(resultSet.getString(7));
				quizList.add(q);
			}
			connectionManager.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quizList;
		
	}

	@Override
	public boolean persistQuiz(User user) {
		
		Connection connection=connectionManager.openConnection();
		int rows=0;
		try {
			
			PreparedStatement statement = connection.prepareStatement(quizdbquery.getPersistUserQuery());
			statement.setString(1,user.getUserName());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();
			
				
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		try {
			PreparedStatement statement1 = connection.prepareStatement(quizdbquery.getPersistUserAuthority());
			statement1.setString(1,user.getUserName());
			statement1.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		try {
			PreparedStatement statement2 = connection.prepareStatement(quizdbquery.getPersistUserDetailsQuery());
			statement2.setString(1,user.getFirstName());
			statement2.setString(2, user.getLastName());
			statement2.setString(3,user.getUserName());
			statement2.setString(4,user.getEmailId());
			statement2.setDate(5,user.getDob());
			statement2.setString(6,user.getState());
			statement2.setString(7,user.getCity());
			statement2.setString(8, user.getAddress());
			statement2.setString(9,user.getGender());
			
			rows = statement2.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		connectionManager.closeConnection();
		if(rows>0)
		return true;
		else
		return false;
	}

	@Override
	public User authUser(String userName) {
		
		Connection connection=connectionManager.openConnection();
		User user = new User();
		
		
		try {
			PreparedStatement statement=connection.prepareStatement(quizdbquery.getSelectUserByUserName());
			statement.setString(1, userName);		
			ResultSet resultSet = statement.executeQuery();
		
			while (resultSet.next()) {
				
				user.setUserName(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				
		    }
			
		} catch (SQLException ex) {
			System.err.println("yha pr exception h");
		}
		connectionManager.closeConnection();
		
		return user;
		
	}

	@Override
	public List<String> getCityByState(String state) {
		Connection connection=connectionManager.openConnection();
		List<String> cityList = new ArrayList<String>();
		
		
		try {
			PreparedStatement statement=connection.prepareStatement(quizdbquery.getSelectCityByState());
			statement.setString(1, state);		
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				cityList.add((resultSet.getString(1)));
		    }
			
		} catch (SQLException ex) {
			System.err.println("yha pr exception h");
		}
		connectionManager.closeConnection();
		
		return cityList;
	}

	@Override
	public List<StateModel> getAllState() {
		Connection connection=connectionManager.openConnection();
		List<StateModel> list = new ArrayList<>();
		
		
		try {
			Statement statement=connection.createStatement();		
			ResultSet resultSet = statement.executeQuery(quizdbquery.getSelectState());
			
			while (resultSet.next()) {
				StateModel state = new StateModel();
				state.setStateCode(resultSet.getString(1));
				state.setStateName(resultSet.getString(2));
				list.add(state);
		    }
			
		} catch (SQLException ex) {
			System.err.println("yha pr exception h");
		}
		connectionManager.closeConnection();
		return list;
	}

	@Override
	public boolean saveScore(ScoreRequest scoreRequest) {
		Connection connection=connectionManager.openConnection();
		int row = 0;
		try {
			PreparedStatement statement=connection.prepareStatement(quizdbquery.getSaveUserScore());
			statement.setString(1, scoreRequest.getSubject());
			statement.setInt(2, scoreRequest.getScore());
			statement.setString(3, scoreRequest.getUsername());
			
			row = statement.executeUpdate();
			
			
			
		} catch (SQLException ex) {
			System.err.println("yha pr exception h");
		}
		connectionManager.closeConnection();	
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public ScoreResponse getScoreByUsername(String username) {
		ScoreResponse scoreResponse = null;
		Connection connection=connectionManager.openConnection();
		
		try {
			PreparedStatement statement=connection.prepareStatement(quizdbquery.getSelectScoreByUsername());
			statement.setString(1, username);		
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				scoreResponse = new ScoreResponse();
				scoreResponse.setUsername(resultSet.getString(1));
				scoreResponse.setScore(resultSet.getInt(2));
				scoreResponse.setSubject(resultSet.getString(3));
		    }
			
		} catch (SQLException ex) {
			System.err.println("yha pr exception h");
		}
		connectionManager.closeConnection();
		
		
		return scoreResponse;
	}

	@Override
	public User getUserDetails(String username) {
		User user = null;
		Connection connection=connectionManager.openConnection();
		
		try {
			PreparedStatement statement=connection.prepareStatement(quizdbquery.getSelectUserDetailsByUsername());
			statement.setString(1, username);		
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				user = new User();
				user.setUserName(resultSet.getString(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setEmailId(resultSet.getString(4));
				user.setAddress(resultSet.getString(5));
				user.setState(resultSet.getString(6));
				user.setCity(resultSet.getString(7));
				user.setDob(resultSet.getDate(8));
				user.setGender(resultSet.getString(9));
		    }
			
		} catch (SQLException ex) {
			System.err.println("yha pr exception h");
		}
		connectionManager.closeConnection();
		
		return user;
	}


}
