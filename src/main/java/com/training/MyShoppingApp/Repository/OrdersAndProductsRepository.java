/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.MyShoppingApp.entity.OrderAndProducts;

public interface OrdersAndProductsRepository
		extends
			JpaRepository<OrderAndProducts, Integer> {

}
