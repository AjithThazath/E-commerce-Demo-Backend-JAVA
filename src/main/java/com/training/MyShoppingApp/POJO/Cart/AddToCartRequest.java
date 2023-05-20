/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.Cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddToCartRequest {

	private String username;

	@Min(value = 1)
	private int quantity;

	@NotNull
	private int productId;

	public String getUsername() {
		return username;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getProductId() {
		return productId;
	}

}
