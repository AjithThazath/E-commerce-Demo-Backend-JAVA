/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.MyShoppingApp.entity.Cart;
import com.training.MyShoppingApp.entity.Product;
import com.training.MyShoppingApp.entity.User;

import jakarta.transaction.Transactional;

@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByuserId(User user);

	Cart findByuserIdAndProducts(User user, Product prod);

	List<Cart> deleteByuserIdAndProducts(User user, Product p);

	void deleteByuserId(User user);
}
