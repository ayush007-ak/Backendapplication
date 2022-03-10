package com.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.quiz.integrate.ConnectionManager;
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private ConnectionManager connectionManager;
	@Override
	public UserDetails getUserDetails(String userName)  {
		
		Connection connection;
		UserDetails userDetails=null;
		try {
			connection = connectionManager.openConnection();
			PreparedStatement statement=connection.prepareStatement("select  u.username,u.password,u.enabled,a.authority"
					+ " from users as u join authorities as a on(u.username=a.username)"
					+ " where u.username=?");
			statement.setString(1, userName);
			ResultSet resultSet=statement.executeQuery();
			List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			String userNameDB=null;
			String password=null;
			while(resultSet.next()) {
				GrantedAuthority authority=new SimpleGrantedAuthority(resultSet.getString("authority"));
				authorities.add(authority);
				userNameDB=resultSet.getString("username");
				password="{noop}"+resultSet.getString("password");
			}
			userDetails=new User(userNameDB,
					password,authorities);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

}
