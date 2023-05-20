/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(String exe) {
		super(exe);
	}

}
