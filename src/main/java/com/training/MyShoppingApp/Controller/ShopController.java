/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyShoppingApp.POJO.Admin.GetAllProductsResPOJO;
import com.training.MyShoppingApp.Repository.ProductRepository;
import com.training.MyShoppingApp.entity.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@Transactional
@Tag(name = "Shop APIs", description = "Common APIs that can be accesed without token")
public class ShopController {

	@Autowired
	ProductRepository prodService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET, value = "/shop/products")
	public GetAllProductsResPOJO mainPage(
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit) {
		Integer skip = null;
		if (page != null && limit != null) {
			skip = page * limit;
		}
		return prodService.getAllProduct(limit, skip);
	}

	@GetMapping("/shop/product/{id}")
	@Operation(summary = "Get product details by id")
	public Product getPorductById(@PathVariable int id) {
		return prodService.getProductById(id);
	}

	// @Operation(summary = "Get All products")
	// @GetMapping("/")
	// public String redirectToGetAllProducts() {
	// return "Welcome common page";
	// }

	// Deprecated APIs
	// changed to REST controller
	// @RequestMapping(method = RequestMethod.GET, value = "/")
	// public String mainPage(ModelMap model) {
	//
	// model.put("products", prodService.getAllProducts());
	// return "index";
	// }

	// @GetMapping("/")
	// public ModelAndView redirectToGetAllProducts() {
	// return new ModelAndView("redirect:/products");
	// }

}
