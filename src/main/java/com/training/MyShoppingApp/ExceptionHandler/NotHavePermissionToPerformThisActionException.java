/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class NotHavePermissionToPerformThisActionException
		extends
			RuntimeException {

	public NotHavePermissionToPerformThisActionException(String exe) {
		super(exe);
	}

}
