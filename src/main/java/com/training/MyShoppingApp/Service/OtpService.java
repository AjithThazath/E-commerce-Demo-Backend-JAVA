/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Service;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.MyShoppingApp.Repository.OTPRepository;
import com.training.MyShoppingApp.entity.OTP;
import com.training.MyShoppingApp.entity.User;
import com.training.MyShoppingApp.enums.OtpStatus;
import com.training.MyShoppingApp.enums.OtpType;

@Service
public class OtpService {

	@Autowired
	private OTPRepository otpRepo;

	public int generateOtp() {
		Random random = new Random();
		int otp = 1000 + random.nextInt(9000);
		return otp;
	}

	public boolean validateOTP(User user, OtpType type, OtpStatus status,
			int reqOtp) {
		OTP otp = otpRepo.findByUseridAndTypeAndStatus(user, type, status,
				new Date());
		if (otp == null) {
			return false;
		}
		if (otp.getOtp() == reqOtp) {
			otp.setStatus(OtpStatus.VERIFICATION_SUCCESFULL);
			otpRepo.save(otp);
			return true;
		} else {
			return false;
		}
	}

}
