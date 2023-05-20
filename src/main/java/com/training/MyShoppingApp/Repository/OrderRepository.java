/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.training.MyShoppingApp.entity.Orders;
import com.training.MyShoppingApp.entity.User;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	Page<Orders> findAllByUser(User user, Pageable pageble);
	Long countByUser(User user);

	// @Query(value = "select
	// o1_0.order_id,o1_0.order_date,o1_0.total_amount,o1_0.user_email from
	// orders o1_0 "
	// + "where o1_0.user_email=?1", nativeQuery = true)
	// List<Orders> getAllOrders(String string);

}
