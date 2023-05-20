/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.entity;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 2, message = "Name should have at least 2 characters")
	private String title;

	@NotNull
	@Column(name = "imageUrl")
	private String imageUrl;

	@NotNull
	@Column(length = 65555)
	private StringBuilder description;

	@NotNull
	private Double price;

	@OneToMany(mappedBy = "products")
	private Set<Cart> cart = new HashSet<Cart>();

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private Set<OrderAndProducts> orders = new HashSet<OrderAndProducts>();

	@Positive
	@NotNull
	@Min(value = 1)
	private int quantity;

	public Product() {
	}

	public Product(String title, String imageUrl, StringBuilder description,
			Double price, int qty) {
		super();
		this.title = title;
		this.imageUrl = imageUrl;
		this.description = description;
		this.price = price;
		this.quantity = qty;
	}

	public Product(int id, String title, String imageUrl,
			StringBuilder description, int qty, Double price) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.description = description;
		this.quantity = qty;
		this.price = price;
	}

	// To create product for order response with ordered quantity
	public Product(String title, String imageUrl, StringBuilder description,
			int qty, Double price) {
		super();
		this.title = title;
		this.imageUrl = imageUrl;
		this.description = description;
		this.quantity = qty;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return URLDecoder.decode(this.imageUrl);
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public StringBuilder getDescription() {
		return description;
	}
	public void setDescription(StringBuilder description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int qty) {
		this.quantity = qty;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	// @Override
	// public String toString() {
	// return "Product [id=" + id + ", title=" + title + ", imageUrl="
	// + imageUrl + ", description=" + description + ", price=" + price
	// + "]" + "Quantity=" + quantity;
	// }

}
