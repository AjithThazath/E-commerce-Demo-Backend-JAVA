/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String exe) {
		super(exe);
	}

}
