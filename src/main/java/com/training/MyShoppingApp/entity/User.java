/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.MyShoppingApp.enums.Role;
import com.training.MyShoppingApp.enums.Sex;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User implements UserDetails {

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// private int id;

	@Id
	private String username;

	@NotNull
	private String name;

	@NotNull
	@JsonIgnore
	private String password;

	@NotNull
	private Long mobile_number;

	@NotNull
	private String address;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "userId")
	private Set<Cart> cart = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private Set<Orders> orders = new HashSet<>();

	public User() {
		super();
	}

	public User(String username, @NotNull String name, @NotNull String password,
			@NotNull @Size(min = 10) Long mobile_number, Role role,
			String address, Sex sex) {
		super();
		// this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.mobile_number = mobile_number;
		this.role = role;
		this.address = address;
		this.sex = sex;
	}

	// public int getId() {
	// return id;
	// }
	//
	// public void setId(int id) {
	// this.id = id;
	// }

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User[ username=" + username + ", name=" + name + ", password="
				+ password + ", mobile_number=" + mobile_number + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getRole() {
		return role.toString();
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders.add(orders);
	}

	public String getSex() {
		return sex.toString();
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

}
