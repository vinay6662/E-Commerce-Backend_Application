package com.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.dao.VerificationTokenDAO;
import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.entity.User;
import com.entity.VerificationToken;
import com.exception.EmailFailureException;
import com.exception.UserAlreadyExistsException;
import com.exception.UserNotVerifiedException;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private EncryptionService encryptionService;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationTokenDAO verificationTokenDAO;

	public User registerUser(UserDTO userDTO)throws UserAlreadyExistsException, EmailFailureException {
	if(userDAO.findByEmailIgnoreCase(userDTO.getEmail()).isPresent()
	|| userDAO.findByUserNameIgnoreCase(userDTO.getUserName()).isPresent()) {
		throw new UserAlreadyExistsException();
	}
		
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(encryptionService.encryptPassword(userDTO.getPassword()));
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		VerificationToken verificationToken =  createVerificationToken(user);
		emailService.sendVerificationEmail(verificationToken);
		verificationTokenDAO.save(verificationToken);
		User savedUser = userDAO.save(user);
		return savedUser;
	}
	
	private VerificationToken createVerificationToken(User user) {
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(jwtService.generateVerificationJWT(user));
		verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		verificationToken.setUser(user);
		user.getVerificationTokens().add(verificationToken);
		return verificationToken;
	}

	public String loginUser(LoginDTO loginDTO) throws EmailFailureException, UserNotVerifiedException {
		Optional<User> loggedUser = userDAO.findByUserNameIgnoreCase(loginDTO.getUserName());
		if(loggedUser.isPresent()) {
			User user = loggedUser.get();
			if(encryptionService.verifyPassword(loginDTO.getPassword(), user.getPassword())) {
				if(user.isEmailVerified()) {
					return jwtService.generateJWT(user);
				}else {
					List<VerificationToken> verificationTokens = user.getVerificationTokens();
				boolean resend = verificationTokens.size() == 0 ||
						verificationTokens.get(0).getCreatedTimeStamp().before(new Timestamp(System.currentTimeMillis()-(60*60*1000)));
				if(resend) {
				   VerificationToken verificationToken = createVerificationToken(user);
				   emailService.sendVerificationEmail(verificationToken);
					verificationTokenDAO.save(verificationToken);
				}
				throw new UserNotVerifiedException(resend);
				
				}
			}
		}
		return null;
	}
	
	@Transactional
	public boolean verifyUser(String token) {
		Optional<VerificationToken> opToken = verificationTokenDAO.findByToken(token);
		if(opToken.isPresent()) {
			VerificationToken verificationToken = opToken.get();
			User user = verificationToken.getUser();
			if(!user.isEmailVerified()) {
				user.setEmailVerified(true);
				userDAO.save(user);
				verificationTokenDAO.deleteByUser(user);
			}
			return true;
		}
		return false;
	}

}
