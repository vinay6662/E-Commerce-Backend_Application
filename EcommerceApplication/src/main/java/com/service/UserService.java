package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.entity.User;
import com.exception.UserAlreadyExistsException;

@Service
public class UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private EncryptionService encryptionService;
	
	@Autowired
	private JWTService jwtService;

	public User registerUser(UserDTO userDTO)throws UserAlreadyExistsException {
	if(userDAO.findByEmailIgnoreCase(userDTO.getEmail()).isPresent()
	|| userDAO.findByUserNameIgnoreCase(userDTO.getUserName()).isPresent()) {
		throw new UserAlreadyExistsException();
	}
		
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		//TODO: Password Encryption
		user.setPassword(encryptionService.encryptPassword(userDTO.getPassword()));
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		User savedUser = userDAO.save(user);
		return savedUser;
	}

	public String loginUser(LoginDTO loginDTO) {
		Optional<User> loggedUser = userDAO.findByUserNameIgnoreCase(loginDTO.getUserName());
		if(loggedUser.isPresent()) {
			User user = loggedUser.get();
			if(encryptionService.verifyPassword(loginDTO.getPassword(), user.getPassword())) {
				return jwtService.generateJWT(user);
			}
		}
		return null;
	}

}
