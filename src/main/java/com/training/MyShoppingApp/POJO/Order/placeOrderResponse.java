/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.Order;

public class placeOrderResponse {

	private int orderId;

	public placeOrderResponse(int orderId) {
		super();
		this.orderId = orderId;
	}

	public placeOrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
