package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.User;
import com.entity.VerificationToken;

public interface VerificationTokenDAO extends JpaRepository<VerificationToken,Long>{

	
	Optional<VerificationToken> findByToken(String token);
	
	void deleteByUser(User user);
}
