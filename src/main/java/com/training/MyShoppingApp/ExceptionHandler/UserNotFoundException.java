/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String exe) {
		super(exe);
	}

}
