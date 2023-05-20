/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.User;

import com.training.MyShoppingApp.enums.Sex;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PutupdateUserDetails {

	private String username;
	private String name;
	private String address;
	private Long mobile_number;

	@Enumerated(EnumType.STRING)
	private Sex sex;
	public PutupdateUserDetails(String username, String name, String address,
			Long mobile_number, Sex sex) {
		super();
		this.username = username;
		this.name = name;
		this.address = address;
		this.mobile_number = mobile_number;
		this.sex = sex;
	}
	public PutupdateUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}
	public Sex getSex() {
		return this.sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}

}
