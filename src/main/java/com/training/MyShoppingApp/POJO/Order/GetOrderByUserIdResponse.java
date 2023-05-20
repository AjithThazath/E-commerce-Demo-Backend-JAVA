/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.training.MyShoppingApp.entity.Orders;
import com.training.MyShoppingApp.entity.Product;
import com.training.MyShoppingApp.enums.OrderStatus;

public class GetOrderByUserIdResponse {

	private int id;
	private List<Product> products = new ArrayList<Product>();
	private Date orderDate;
	private Double totalAmount;
	private OrderStatus status;

	@JsonInclude(Include.NON_NULL)
	private String userid;

	public GetOrderByUserIdResponse() {
		super();
	}

	public GetOrderByUserIdResponse(Orders order, List<Product> product,
			Double amount, OrderStatus status, String userId) {
		this.id = order.getId();
		this.products = product;
		this.orderDate = order.getOrderDate();
		this.totalAmount = amount;
		this.status = status;
		this.userid = userId;
	}

	public GetOrderByUserIdResponse(Orders order, List<Product> product,
			Double amount, OrderStatus status) {
		this.id = order.getId();
		this.products = product;
		this.orderDate = order.getOrderDate();
		this.totalAmount = amount;
		this.status = status;
		this.userid = null;
	}

	public int getId() {
		return id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
