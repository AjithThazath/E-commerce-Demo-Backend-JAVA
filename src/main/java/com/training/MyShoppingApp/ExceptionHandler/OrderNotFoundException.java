/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(String exe) {
		super(exe);
	}
}
