/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.User;

import com.training.MyShoppingApp.entity.User;

public class GetUserDetails {

	private String username;
	private String name;
	private String address;
	private Long mobile_number;
	private String sex;

	public GetUserDetails(User user) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.address = user.getAddress();
		this.mobile_number = user.getMobile_number();
		this.sex = user.getSex();
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
