/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "USER_ID")
	private User userId;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product products;

	@NotNull
	@Min(value = 1)
	private int quantity;

	private Date createdDate;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(User user, Product product, int quantity) {
		super();
		this.userId = user;
		this.products = product;
		this.quantity = quantity;
		this.createdDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User user_id) {
		this.userId = user_id;
	}

	public Product getProduct_id() {
		return products;
	}

	public void setProduct_id(Product product) {
		this.products = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
