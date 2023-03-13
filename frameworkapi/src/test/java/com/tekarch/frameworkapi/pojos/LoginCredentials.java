package com.tekarch.frameworkapi.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginCredentials {

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;

	
	public LoginCredentials(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
