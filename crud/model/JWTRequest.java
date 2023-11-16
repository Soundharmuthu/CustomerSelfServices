package com.crud.model;

import java.io.Serializable;

public class JWTRequest implements Serializable {
	
	private static final long serialVersionUID = 4049926817467332024L;
	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JWTRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public JWTRequest() {
		super();
	}

}
