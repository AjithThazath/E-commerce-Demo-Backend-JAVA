/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.Order;

import java.util.List;

public class GetOrderPOJO {

	private Long totalCount;

	private List<GetOrderByUserIdResponse> orders;

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<GetOrderByUserIdResponse> getOrders() {
		return orders;
	}

	public void setOrders(List<GetOrderByUserIdResponse> orders) {
		this.orders = orders;
	}

	public GetOrderPOJO(Long totalPages,
			List<GetOrderByUserIdResponse> orders) {
		super();
		this.totalCount = totalPages;
		this.orders = orders;
	}

}
