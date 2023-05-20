/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.training.MyShoppingApp.entity.OTP;
import com.training.MyShoppingApp.entity.User;
import com.training.MyShoppingApp.enums.OtpStatus;
import com.training.MyShoppingApp.enums.OtpType;

public interface OTPRepository extends JpaRepository<OTP, Integer> {

	@Query("select a from OTP a where a.userid = :userid and a.type =:type and a.status =:status and a.expiry >= :date ORDER BY id DESC LIMIT 1")
	public OTP findByUseridAndTypeAndStatus(@Param("userid") User user,
			@Param("type") OtpType type, @Param("status") OtpStatus status,
			@Param("date") Date date);
}
