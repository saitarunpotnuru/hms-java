package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sshealthcare.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

	

}
