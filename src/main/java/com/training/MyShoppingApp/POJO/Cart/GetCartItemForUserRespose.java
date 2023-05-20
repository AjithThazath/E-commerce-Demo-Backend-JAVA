/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.Cart;

import java.util.ArrayList;

import com.training.MyShoppingApp.entity.Product;

public class GetCartItemForUserRespose {

	private Double total;

	private ArrayList<Product> items = new ArrayList<Product>();

	public GetCartItemForUserRespose() {
		super();
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public ArrayList<Product> getItems() {
		return items;
	}

	public void setItems(ArrayList<Product> items) {
		this.items = items;
	}

	public GetCartItemForUserRespose(Double total, ArrayList<Product> items) {
		super();
		this.total = total;
		this.items = items;
	}

}
