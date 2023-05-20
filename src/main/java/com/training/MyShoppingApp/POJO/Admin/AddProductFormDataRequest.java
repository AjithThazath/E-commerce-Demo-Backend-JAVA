/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.POJO.Admin;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddProductFormDataRequest {

	private Integer id;
	@NotNull(message = "Title cannot be Null")
	private String title;
	@NotNull(message = "Description cannot be Null")
	private StringBuilder description;
	@NotNull(message = "Price cannot be Null")
	private Double price;
	@NotNull(message = "Quantity cannot be Null")
	@Min(value = 1, message = "Quantity should be 1 or greater")
	private int quantity;

	private MultipartFile image;

	public AddProductFormDataRequest(Integer id, @NotNull String title,
			@NotNull StringBuilder description, @NotNull Double price,
			@NotNull @Min(1) int quantity, MultipartFile image) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public StringBuilder getDescription() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public MultipartFile getImage() {
		return image;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(StringBuilder description) {
		this.description = description;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
