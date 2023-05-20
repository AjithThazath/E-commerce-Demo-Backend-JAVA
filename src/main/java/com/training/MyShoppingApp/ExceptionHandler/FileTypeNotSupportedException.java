/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

public class FileTypeNotSupportedException extends RuntimeException {

	public FileTypeNotSupportedException(String exe) {
		super(exe);
	}

}
