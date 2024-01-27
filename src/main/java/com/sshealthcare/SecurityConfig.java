package com.sshealthcare;

	

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
		protected void configure(AuthenticationManagerBuilder auth)throws Exception{
			System.out.println("configure...called");
			auth.authenticationProvider(getProvider());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			.antMatchers("/user/login","/auth/login",
						"/patient/add","/patient/get",
						"/doctor/add","/doctor/all","/doctor/getone/{id}","/doctor/getwithname","/doctor/delete/{id}","/doctor/update/{id}",
						"/receptionist/add","/receptionist/all","/receptionist/delete/{id}","/receptionist/update/{id}",
						"/admin/add",
						"/department/add","/department/get",
						"/room/add","/room/all","/doctor//updateAppointment/{Aid}",
						"/addmission/add","/admission//add/{rid}/{patientId}/{did}","/admission/all",
						"/billing/add",
						"/appointment/get/appointment/{patientId}","/appointment/get/{did}","/appointment/add/{pid}/{did}","appointment/{pdid}").permitAll()
			.antMatchers(HttpMethod.POST,"/user/login").authenticated()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and()
			.csrf().disable()
			.cors().disable();
		
		}
		
		
		@Bean
		public Logger getLogger() {
			return LoggerFactory.getLogger("Log Records");
		}
		
		
		@Bean
		public PasswordEncoder getEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		public DaoAuthenticationProvider getProvider() {
			DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
			dao.setPasswordEncoder(getEncoder());
			dao.setUserDetailsService(userService);
			
			return dao;
		}
	}












//package com.sshealthcare;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.sshealthcare.service.UserService;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserService userService;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//	
//		System.out.println("configure....called...");
//		auth.authenticationProvider(getProvider());
//		
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//		.authorizeRequests()
//		.antMatchers(
//
//				"/patient/add","/patient/get","/patient/get/{pid}","/patient/appointment/{pid}","/patient/delete/{id}","/patient/update/{id}",
//
//				"/room/add","/room/all","/room/getone/{id}",
//				
//				"/executive/add","/executive/get","/executive/get/{id}","/executive/update/{id}",
//				
//				"/appointment/add/{pid}/{did}","/appointment/all","/appointment/getone/{id}","/appointment/get/appointment/{patientId}",
//
//
//				"/patient/add","/patient/get","/patient/get/{pid}","/patient/delete/{id}","/patient/update/{id}",
//				
//				"/room/add","/room/all","/room/getone/{id}",
//				
//				"/executive/add","/executive/get","/executive/get/{id}","/executive/update/{id}",
//				
//				"/appointment/add/{pid}/{did}","/appointment/all","/appointment/get/patient/{patientId}","/appointment/get/{doctorId}",
//				
//
//				"/doctor/add/{depid}","/doctor/all","/doctor/getone/{id}","/doctor/update/{id}","/doctor/delete/{id}","/appointment/Appointment/{pdid}",
//				
//				"/receptionist/add","/receptionist/all","/receptionist/getone/{id}","/receptionist/update/{id}","/receptionist/delete/{id}",
//				
//				"/admission/add/{rid}/{patientId}/{did}","/admission/all","/admission/getone/{id}","/admission/update/{id}",
//				
//				"/department/add","/department/get","/department/get/{did}","/user/login",
//				
//				"/billing/add/{admissionId}/{pid}/{did}","/billing/all","/billing/getone/{id}","/billing/update/{id}","/doctor/getwithname").permitAll()
//		
//		
//		.antMatchers(HttpMethod.POST,"/user/login").authenticated()
//		.anyRequest().authenticated()
//		.and().httpBasic()
//		.and()
//		.csrf().disable()
//		.cors().disable();
//	}
//	
//	@Bean
//	public PasswordEncoder getEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	public DaoAuthenticationProvider getProvider() {
//		System.out.println("getProvider....called....");
//		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//		//also, I want spring to know that I have encrypted password in the db dao. setPasswordEncoder (getEncoder());
//		//from here.. i want spring to go to my database and fetch users. dao. setUserDetailsService(userService); //UserDetailsService : UserService
//		return dao;
//	}
//}