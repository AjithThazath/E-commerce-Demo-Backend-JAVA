/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.MyShoppingApp.ExceptionHandler.InvalidQueryParameterException;
import com.training.MyShoppingApp.ExceptionHandler.UserNotFoundException;
import com.training.MyShoppingApp.POJO.Cart.AddToCartRequest;
import com.training.MyShoppingApp.POJO.Cart.GetCartItemForUserRespose;
import com.training.MyShoppingApp.POJO.Cart.PutQuantityChangeBody;
import com.training.MyShoppingApp.Repository.CartRepository;
import com.training.MyShoppingApp.Repository.ProductRepository;
import com.training.MyShoppingApp.Repository.UserRepository;
import com.training.MyShoppingApp.constants.constants;
import com.training.MyShoppingApp.entity.Cart;
import com.training.MyShoppingApp.entity.Product;
import com.training.MyShoppingApp.entity.User;
import com.training.MyShoppingApp.enums.QuantityChangeReq;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/cart")
@Transactional
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Cart APIs", description = "Cart APIs to add/edit/delete for User APIs")
public class CartController {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository prodRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/addToCart")
	@Operation(summary = "Add to cart", description = "Add item to user cart")
	public GetCartItemForUserRespose addToCart(
			@RequestBody AddToCartRequest reqBody, HttpServletRequest req)
			throws JsonProcessingException {
		User user = userRepository
				.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		Product prod = prodRepository.getProductById(reqBody.getProductId());
		Cart existingCart = cartRepository.findByuserIdAndProducts(user, prod);
		if (existingCart != null) {
			existingCart.setQuantity(
					existingCart.getQuantity() + reqBody.getQuantity());
			cartRepository.save(existingCart);
			return getCartForUser(req);
		}
		Cart item = new Cart(user, prod, reqBody.getQuantity());
		cartRepository.save(item);
		return getCartForUser(req);
	}

	@GetMapping("/getCartForUser")
	@Operation(summary = "Get Cart Items", description = "Get cart items for the user")
	public GetCartItemForUserRespose getCartForUser(HttpServletRequest req)
			throws JsonProcessingException {
		User user = userRepository
				.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(user));
		List<Cart> items = cartRepository.findByuserId(user);
		ArrayList<Product> allCart = new ArrayList<Product>();
		Double total = 0.0;
		for (Cart cart : items) {
			allCart.add(new Product(cart.getProduct_id().getId(),
					cart.getProduct_id().getTitle(),
					cart.getProduct_id().getImageUrl(),
					cart.getProduct_id().getDescription(), cart.getQuantity(),
					cart.getProduct_id().getPrice()));
			total = (total
					+ (cart.getProduct_id().getPrice() * cart.getQuantity()));
		}
		GetCartItemForUserRespose response = new GetCartItemForUserRespose(
				total, allCart);

		// List<Cart> itemsToTest = cartRepository.findByuserIdAndProducts(user,
		// allCart.get(2));
		// ObjectMapper mapper = new ObjectMapper();
		// System.out.println(mapper.writerWithDefaultPrettyPrinter()
		// .writeValueAsString(itemsToTest));
		return response;
	}

	@DeleteMapping("/remove/{productId}")
	@Operation(summary = "Remove item form cart", description = "Remove item form cart")
	public GetCartItemForUserRespose removeFromCart(@PathVariable int productId,
			HttpServletRequest req) throws JsonProcessingException {
		Product p = prodRepository.getProductById(productId);
		User user = userRepository
				.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		cartRepository.deleteByuserIdAndProducts(user, p);
		return getCartForUser(req);
	}

	@PutMapping("/quantityChange/{productId}")
	@Operation(summary = "Change quantity of item in cart", description = "change quantity of item in cart")
	public GetCartItemForUserRespose ChangeQuantity(
			@RequestParam(required = true, name = "type") String reqType,
			@PathVariable int productId, HttpServletRequest req)
			throws JsonProcessingException {
		Product p = prodRepository.getProductById(productId);
		User user = userRepository
				.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		Cart item = cartRepository.findByuserIdAndProducts(user, p);
		if (reqType.equals(QuantityChangeReq.ADD.toString())) {
			item.setQuantity(item.getQuantity() + 1);
		} else if (reqType.equals(QuantityChangeReq.REMOVE.toString())) {
			item.setQuantity(item.getQuantity() - 1);
		} else {
			throw new InvalidQueryParameterException(
					constants.INVALID_QUERY_PARAM + reqType);
		}
		if (item.getQuantity() == 0) {
			removeFromCart(productId, req);
		}
		return getCartForUser(req);

	}

	@PutMapping("/changeQuantity")
	@Operation(summary = "Change quantity of item in cart", description = "change quantity of item in cart")
	public GetCartItemForUserRespose quantityChange(
			@RequestBody PutQuantityChangeBody reqBody, HttpServletRequest req)
			throws JsonProcessingException {
		Product p = prodRepository.getProductById(reqBody.getProductId());
		User user = userRepository
				.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		Cart item = cartRepository.findByuserIdAndProducts(user, p);
		item.setQuantity(reqBody.getQuantity());
		return getCartForUser(req);

	}

}
