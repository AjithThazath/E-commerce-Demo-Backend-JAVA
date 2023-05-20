/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.OTP;

import jakarta.validation.constraints.NotNull;

public class ValidateOtpReuestBody {

	@NotNull
	private int otp;

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

}
