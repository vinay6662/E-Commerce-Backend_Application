package com.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotNull
    @NotBlank(message = "username is required.")
	@Size(min = 1, max = 225, message = "username is too long.")
	private String userName;
	
	@NotNull
    @NotBlank(message = "password is required.")
	@Size(min = 8, message = "The new password is too short.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String password;
	@NotNull
    @NotBlank(message = "Email is required.")
	@Email
	private String email;
	@NotNull
    @NotBlank(message = "firstName is required.")
	private String firstName;
	@NotNull
    @NotBlank(message = "lastName is required.")
	private String lastName;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserDTO(String userName, String password, String email, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserDTO() {

	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
