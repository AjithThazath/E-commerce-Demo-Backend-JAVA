/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class OTPValidationFailed extends RuntimeException {

	public OTPValidationFailed(String exe) {
		super(exe);
	}

}
