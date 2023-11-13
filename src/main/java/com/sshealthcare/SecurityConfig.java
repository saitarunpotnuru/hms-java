package com.sshealthcare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sshealthcare.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	
		System.out.println("configure....called...");
		auth.authenticationProvider(getProvider());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/admission/add/{rid}/{patientId}/{did}","/admission/all","/admission/getone/{id}","/admission/update/{id}",
				"/patient/add","/patient/get","/patient/get/{pid}","/patient/delete/{id}","/patient/update/{id}",
				"/room/add","/room/all","/room/getone/{id}",
				"/executive/add","/executive/get",
				"/doctor/add/{depid}","/doctor/all","/doctor/getone/{id}","/doctor/update/{id}",
				"/receptionist/add","/receptionist/all","/receptionist/getone/{id}","/receptionist/update/{id}",
				"/department/add","/department/get","/department/get/{did}",
				"/billing/add/{admissionId}/{pid}/{did}","/billing/all","/billing/getone/{id}","/billing/update/{id}").permitAll()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.cors().disable();
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	public DaoAuthenticationProvider getProvider() {
		System.out.println("getProvider....called....");
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		//also, I want spring to know that I have encrypted password in the db dao. setPasswordEncoder (getEncoder());
		//from here.. i want spring to go to my database and fetch users. dao. setUserDetailsService(userService); //UserDetailsService : UserService
		return dao;
	}
}