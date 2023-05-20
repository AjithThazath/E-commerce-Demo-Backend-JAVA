/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class InvalidQueryParameterException extends RuntimeException {

	public InvalidQueryParameterException(String exe) {
		super(exe);
	}
}
