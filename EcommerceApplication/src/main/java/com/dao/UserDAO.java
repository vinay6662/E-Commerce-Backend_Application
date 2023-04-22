package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
	
	Optional<User>findByUserNameIgnoreCase(String userName);
	
	Optional<User>findByEmailIgnoreCase(String email);

	

}
