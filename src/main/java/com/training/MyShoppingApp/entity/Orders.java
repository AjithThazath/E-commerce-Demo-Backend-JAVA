/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.MyShoppingApp.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Orders {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_email", referencedColumnName = "username")
	@JsonIgnore
	private User user;

	// @ManyToMany
	// @JoinTable(joinColumns = @JoinColumn(name = "order_id",
	// referencedColumnName = "order_id"), inverseJoinColumns = @JoinColumn(name
	// = "product_id", referencedColumnName = "id"))
	// private Set<Product> products = new HashSet<Product>();
	//
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderAndProducts> products;

	private Date orderDate;

	private Double totalAmount;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Orders() {
	}

	public Orders(User user, Double amount, OrderStatus status) {
		super();
		this.user = user;
		this.setTotalAmount(amount);
		this.orderDate = new Date();
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderAndProducts> getProducts() {
		return products;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return this.status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", user=" + user + ", products=" + products
				+ ", orderDate=" + orderDate + ", totalAmount=" + totalAmount
				+ ", status=" + status + "]";
	}

}
