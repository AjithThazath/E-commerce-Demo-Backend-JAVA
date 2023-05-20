/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class TokenExpiredException extends Exception {
	public TokenExpiredException(String exe) {
		super(exe);
	}
}
