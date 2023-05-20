/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.training.MyShoppingApp.ExceptionHandler.ProductNotFoundException;
import com.training.MyShoppingApp.constants.constants;
import com.training.MyShoppingApp.entity.Product;

@Service
public class ProductService {

	ArrayList<Product> productList = new ArrayList<Product>();

	public ProductService() {

	}

	public void addProduct(Product prod) {
		productList.add(prod);
	}

	public ArrayList<Product> getAllProducts() {
		return productList;
	}

	public int getSize() {
		return productList.size();
	}

	public Product getProductById(int id) throws ProductNotFoundException {
		if (id > productList.size()) {
			throw new ProductNotFoundException(
					constants.PRODUCT_NOT_FOUND + id);
		}
		Product p = productList.get(id - 1);
		return p;
	}

	public int deleteProductById(int id) {
		if (id >= 0 && productList.size() >= id) {
			throw new ProductNotFoundException(
					constants.PRODUCT_NOT_FOUND + id);
		} else {
			return 0;
		}
	}

}
