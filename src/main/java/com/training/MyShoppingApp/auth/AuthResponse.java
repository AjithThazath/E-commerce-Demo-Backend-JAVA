/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.auth;

public class AuthResponse {
	private String token;

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
