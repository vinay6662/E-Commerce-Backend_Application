package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginDTO;
import com.dto.LoginResponseDTO;
import com.dto.UserDTO;
import com.entity.User;
import com.exception.EmailFailureException;
import com.exception.UserAlreadyExistsException;
import com.exception.UserNotVerifiedException;
import com.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity userRegistration(@Valid @RequestBody UserDTO userDTO) {
		try {
			userService.registerUser(userDTO);
			return ResponseEntity.ok().build();
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch(EmailFailureException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@PostMapping("/verify")
	public ResponseEntity verifyEmail(@RequestParam String token) {
		if(userService.verifyUser(token)) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
		String jwt;
		try {
			jwt = userService.loginUser(loginDTO);
		} catch (EmailFailureException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (UserNotVerifiedException e) {
			LoginResponseDTO response = new LoginResponseDTO();
			response.setSuccess(false);
			String reason = "USER_NOT_VERIFIED";
			if(e.isNewEmailSent()) {
				reason += "_EMAIL_RESENT";
			}
			response.setFailureReason(reason);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}

		if (jwt == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			LoginResponseDTO response = new LoginResponseDTO();
			response.setJwt(jwt);
			response.setSuccess(true);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("/profile")
	public User getLoggedInUserProfile(@AuthenticationPrincipal User user) {
		return user;
	}

}
