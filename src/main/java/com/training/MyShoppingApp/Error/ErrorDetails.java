/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Error;

import java.util.Date;

public class ErrorDetails {

	private int statusCode;
	private String errorMessage;
	private String details;
	private Date time;

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public ErrorDetails(int statusCode, String errorMessage, String details) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.details = details;
		this.time = new Date();
	}

	@Override
	public String toString() {
		return "ErrorDetails [statusCode=" + statusCode + ", errorMessage="
				+ errorMessage + ", details=" + details + "]";
	}

}
