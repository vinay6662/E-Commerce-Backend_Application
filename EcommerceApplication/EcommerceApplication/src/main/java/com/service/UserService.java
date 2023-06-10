package com.service;

import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.entity.User;
import com.exception.EmailFailureException;
import com.exception.UserAlreadyExistsException;
import com.exception.UserNotVerifiedException;

public interface UserService {
	
	User registerUser(UserDTO userDTO)throws UserAlreadyExistsException, EmailFailureException;
	
	String loginUser(LoginDTO loginDTO)throws EmailFailureException, UserNotVerifiedException;

	boolean verifyUser(String token);
}
