/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.auth;

import com.training.MyShoppingApp.enums.Sex;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RegisterBody {

	// private int Id;

	private String username;

	private String name;

	private String password;

	private Long mobile_number;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	private String address;

	public RegisterBody(int id, String username, String name, String password,
			Long mobile_number, String address, Sex sex) {
		super();
		// this.Id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.mobile_number = mobile_number;
		// this.roles = Role.USER;
		this.address = address;
		this.sex = sex;
	}

	public RegisterBody() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}

	// public int getId() {
	// return Id;
	// }
	//
	// public void setId(int id) {
	// Id = id;
	// }

	// public Role getRoles() {
	// return roles;
	// }
	//
	// public void setRoles(Role roles) {
	// this.roles = roles;
	// }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

}
