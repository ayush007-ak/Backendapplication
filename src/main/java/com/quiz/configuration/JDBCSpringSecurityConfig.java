package com.quiz.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

//@Order(value=200)
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class JDBCSpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserDetailsService userDetailsService;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//	
//		.antMatchers("api/**").authenticated()
//		.anyRequest().permitAll()
//		.and().httpBasic()
//		.authenticationEntryPoint(authEntryPoint);
		
		http.csrf().disable().authorizeRequests()
		.antMatchers("/private/**").authenticated()
		.antMatchers("/private/*").authenticated()
		.antMatchers("/public/*").permitAll()
		.and().httpBasic()
		.authenticationEntryPoint(authEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
