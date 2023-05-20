/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.MyShoppingApp.enums.OtpStatus;
import com.training.MyShoppingApp.enums.OtpType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "OTP")
public class OTP {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@ManyToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	@JsonIgnore
	@NotNull
	private User userid;

	@NotNull
	private Date expiry;

	@NotNull
	@Enumerated(EnumType.STRING)
	private OtpType type;

	@NotNull
	@Enumerated(EnumType.STRING)
	private OtpStatus status;

	@NotNull
	private int otp;

	public OTP() {
		super();

	}

	public OTP(@NotNull User user, @NotNull int otp, @NotNull Date expiry,
			@NotNull OtpType type, @NotNull OtpStatus status) {
		super();
		this.userid = user;
		this.expiry = expiry;
		this.type = type;
		this.otp = otp;
		this.status = status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public User getUser() {
		return userid;
	}

	public void setUser(User user) {
		this.userid = user;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public OtpType getType() {
		return type;
	}

	public void setType(OtpType type) {
		this.type = type;
	}

	public OtpStatus getStatus() {
		return status;
	}

	public void setStatus(OtpStatus status) {
		this.status = status;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "OTP [Id=" + Id + ", userid=" + userid + ", expiry=" + expiry
				+ ", type=" + type + ", status=" + status + ", otp=" + otp
				+ "]";
	}

}
