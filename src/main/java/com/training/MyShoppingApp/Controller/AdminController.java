/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyShoppingApp.ExceptionHandler.NotHavePermissionToPerformThisActionException;
import com.training.MyShoppingApp.ExceptionHandler.ProductNotFoundException;
import com.training.MyShoppingApp.POJO.Admin.AddProductFormDataRequest;
import com.training.MyShoppingApp.POJO.Order.GetOrderByUserIdResponse;
import com.training.MyShoppingApp.POJO.Order.GetOrderPOJO;
import com.training.MyShoppingApp.Repository.OrderRepository;
import com.training.MyShoppingApp.Repository.ProductRepository;
import com.training.MyShoppingApp.Service.FileSystemStorageService;
import com.training.MyShoppingApp.constants.constants;
import com.training.MyShoppingApp.entity.OrderAndProducts;
import com.training.MyShoppingApp.entity.Orders;
import com.training.MyShoppingApp.entity.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "bearerAuth")
@Transactional
@Tag(name = "Admin APIs", description = "Admin APIs to add/edit/delete products")
public class AdminController {

	@Autowired
	ProductRepository prodService;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	FileSystemStorageService fileSystemStorageService;

	@Autowired
	HttpServletRequest request;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value = "/add-product")
	@Operation(summary = "Add /edit products", description = "Add/edit product to shop")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Product addProduct(
			@Valid @ModelAttribute AddProductFormDataRequest prod) {
		if (prod.getId() == null) {
			String imageUrl = fileSystemStorageService
					.saveFile(prod.getImage());
			Product newprod = new Product(prod.getTitle(), imageUrl,
					prod.getDescription(), prod.getPrice(), prod.getQuantity());
			prodService.save(newprod);
			return newprod;
		} else {
			Product existingProd = prodService.getProductById(prod.getId());
			existingProd.setPrice(prod.getPrice());
			existingProd.setDescription(prod.getDescription());
			existingProd.setTitle(prod.getTitle());
			existingProd.setQuantity(prod.getQuantity());
			if (prod.getImage() != null) {
				String imageUrl = fileSystemStorageService
						.saveFile(prod.getImage());
				existingProd.setImageUrl(imageUrl);
			}
			prodService.save(existingProd);
			return existingProd;
		}
	}

	@DeleteMapping("/delete-product/{id}")
	@Operation(summary = "Delete Product by ID", description = "Delete Product by ID")
	public Product deletePorductById(@PathVariable int id)
			throws ProductNotFoundException {
		return prodService.delete(id);

	}

	// @RequestMapping(method = RequestMethod.POST, value =
	// "/edit-product/{id}")
	// public Product geteditProductPage(@PathVariable int id,
	// @RequestBody EditProductPayloadPOJO payload) throws Exception {
	//
	// Product p = prodService.getProductById(id);
	// p.setTitle(payload.getTitle());
	// p.setDescription(payload.getDescription());
	// p.setImageUrl(payload.getImageUrl());
	// p.setPrice(payload.getPrice());
	// return p;
	// }

	@GetMapping("/allorders")
	@Operation(summary = "Get All Orders for Admin", description = "Get All Orders")
	public GetOrderPOJO getAllOrderForAdmin(HttpServletRequest req,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {

		if ((boolean) req.getAttribute("isAdmin")) {
			Pageable page = PageRequest.of(pageNo, pageSize);
			Page<Orders> orders = orderRepo.findAll(page);
			Long totalPage = orderRepo.count();
			List<GetOrderByUserIdResponse> response = new ArrayList<GetOrderByUserIdResponse>();
			for (Orders order : orders) {
				System.out.println(order.toString());
				List<Product> productLstForOrder = new ArrayList<Product>();
				List<OrderAndProducts> OrderProducts = order.getProducts();
				for (OrderAndProducts ordProd : OrderProducts) {
					Product prod = ordProd.getProduct();
					productLstForOrder
							.add(new Product(prod.getId(), prod.getTitle(),
									prod.getImageUrl(), prod.getDescription(),
									ordProd.getQuantity(), prod.getPrice()));

				}
				response.add(new GetOrderByUserIdResponse(order,
						productLstForOrder, order.getTotalAmount(),
						order.getStatus(), order.getUser().getUsername()));
			}
			return new GetOrderPOJO(totalPage, response);
		} else {
			logger.info("Error from getAllOrderForAdmin: "
					+ constants.NO_PERMISSION);
			throw new NotHavePermissionToPerformThisActionException(
					constants.NO_PERMISSION);
		}
	}

	// @depecreated APIs

	// changed to REST CONTROLLER
	// @RequestMapping(method = RequestMethod.GET, value = "/add-product")
	// public String getAddProductPage() {
	// return "addProduct";
	// }

	// chnages to rest controller
	// @PostMapping(value = "/add-product")
	// public RedirectView addProduct(@ModelAttribute("product") Product
	// newprod) {
	// logger.info("new product {}", newprod);
	// newprod.setId(prodService.getSize());
	// prodService.addProduct(newprod);
	// RedirectView redirectView = new RedirectView();
	// redirectView.setUrl("/");
	// return redirectView;
	// }

}
