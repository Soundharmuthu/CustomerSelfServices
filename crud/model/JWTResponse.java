package com.crud.model;

import java.io.Serializable;

public class JWTResponse implements Serializable  {
	

	private static final long serialVersionUID = -2924761790075000678L;
	
	private final String jwttoken ;

	public JWTResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}

	public String getJwttoken() {
		return jwttoken;
	}
	
	

}
