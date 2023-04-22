package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginDTO;
import com.dto.LoginResponseDTO;
import com.dto.UserDTO;
import com.entity.User;
import com.exception.UserAlreadyExistsException;
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

	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO>loginUser(@Valid @RequestBody LoginDTO loginDTO) {
		String jwt = userService.loginUser(loginDTO);
		
		if(jwt == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else {
			LoginResponseDTO response = new LoginResponseDTO();
			response.setJwt(jwt);
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping("/profile")
	public User getLoggedInUserProfile(@AuthenticationPrincipal User user) {
		return user;
		
	}

}
