/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.Admin;

import java.util.List;

import com.training.MyShoppingApp.entity.Product;

public class GetAllProductsResPOJO {

	private Long total;
	private List<Product> products;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public GetAllProductsResPOJO(Long resultSize, List<Product> products) {
		super();
		this.total = resultSize;
		this.products = products;
	}

}
