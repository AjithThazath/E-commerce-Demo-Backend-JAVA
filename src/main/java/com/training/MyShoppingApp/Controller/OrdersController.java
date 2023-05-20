/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyShoppingApp.ExceptionHandler.OTPValidationFailed;
import com.training.MyShoppingApp.ExceptionHandler.OrderNotFoundException;
import com.training.MyShoppingApp.ExceptionHandler.UserNotFoundException;
import com.training.MyShoppingApp.POJO.Cart.statusUpdatesResPOJO;
import com.training.MyShoppingApp.POJO.Common.GeneralMessage;
import com.training.MyShoppingApp.POJO.OTP.ValidateOtpReuestBody;
import com.training.MyShoppingApp.POJO.Order.GetOrderByUserIdResponse;
import com.training.MyShoppingApp.POJO.Order.GetOrderPOJO;
import com.training.MyShoppingApp.POJO.Order.placeOrderResponse;
import com.training.MyShoppingApp.Repository.CartRepository;
import com.training.MyShoppingApp.Repository.OTPRepository;
import com.training.MyShoppingApp.Repository.OrderRepository;
import com.training.MyShoppingApp.Repository.OrdersAndProductsRepository;
import com.training.MyShoppingApp.Repository.ProductRepository;
import com.training.MyShoppingApp.Repository.UserRepository;
import com.training.MyShoppingApp.Service.OtpService;
import com.training.MyShoppingApp.Service.PdfService;
import com.training.MyShoppingApp.constants.constants;
import com.training.MyShoppingApp.email.EmailDetails;
import com.training.MyShoppingApp.email.EmailServiceImpl;
import com.training.MyShoppingApp.entity.Cart;
import com.training.MyShoppingApp.entity.OTP;
import com.training.MyShoppingApp.entity.OrderAndProducts;
import com.training.MyShoppingApp.entity.Orders;
import com.training.MyShoppingApp.entity.Product;
import com.training.MyShoppingApp.entity.User;
import com.training.MyShoppingApp.enums.OrderStatus;
import com.training.MyShoppingApp.enums.OtpStatus;
import com.training.MyShoppingApp.enums.OtpType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("order")
@SecurityRequirement(name = "bearerAuth")
@Transactional
@Tag(name = "Order APIs", description = "Order APIs to add/edit/delete for User APIs")
public class OrdersController {
	Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private OrdersAndProductsRepository orderProdRepo;

	@Autowired
	private OTPRepository otpRepo;

	@Autowired
	private OtpService otpService;

	@Autowired
	private EmailServiceImpl emailImpl;

	@Autowired
	private PdfService pdfService;

	@GetMapping()
	@Operation(summary = "Get All Orders for the logged in User", description = "Get All Orders for the logged in User")
	public GetOrderPOJO getAllOrderByUserId(HttpServletRequest req,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		User user = userRepo.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		PageRequest page = PageRequest.of(pageNo, pageSize);
		Page<Orders> orders = orderRepo.findAllByUser(user, page);
		Long totalPage = orderRepo.countByUser(user);
		List<GetOrderByUserIdResponse> response = new ArrayList<GetOrderByUserIdResponse>();
		for (Orders order : orders) {
			System.out.println(order.toString());
			List<Product> productLstForOrder = new ArrayList<Product>();
			List<OrderAndProducts> OrderProducts = order.getProducts();
			for (OrderAndProducts ordProd : OrderProducts) {
				Product prod = ordProd.getProduct();
				productLstForOrder.add(new Product(prod.getTitle(),
						prod.getImageUrl(), prod.getDescription(),
						ordProd.getQuantity(), prod.getPrice()));

			}
			response.add(new GetOrderByUserIdResponse(order, productLstForOrder,
					order.getTotalAmount(), order.getStatus()));
		}
		return new GetOrderPOJO(totalPage, response);
	}

	@GetMapping("/generateOTP")
	@Operation(summary = "Generate OTP")
	public ResponseEntity<GeneralMessage> generateOtpForConfirmation(
			HttpServletRequest req) {
		User user = userRepo.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		int otp = otpService.generateOtp();
		OTP otpGen = new OTP(user, otp,
				new Date(System.currentTimeMillis() + 300000),
				OtpType.CONFIRM_ORDER, OtpStatus.INITIATED);
		otpRepo.save(otpGen);
		Map<String, Object> model = new HashMap();
		model.put("name", user.getName());
		model.put("content", constants.OTP_GENERATED_CONTENT_ORDER + otp);
		try {
			emailImpl.sendSimpleMail(new EmailDetails(user.getUsername(),
					constants.OTP_GENERATED_SUBJECT, model));
		} catch (Exception e) {
			logger.info("Error Sending Email: " + e.getMessage());
		}

		return new ResponseEntity(new GeneralMessage(constants.OTP_GENERATED),
				HttpStatus.OK);

	}

	@GetMapping("/generateInvoice/{orderId}")
	public void generateInvoice(
			@PathVariable(name = "orderId", required = true) int id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Orders order = orderRepo.findById(id).orElseThrow();
		if (order != null) {
			response.setContentType("application/pdf");
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
			String currentDateTime = dateFormat.format(new Date());
			String headerkey = "Content-Disposition";
			String headervalue = "attachment; filename=student"
					+ currentDateTime + ".pdf";
			response.setHeader(headerkey, headervalue);
			pdfService.generateInvoice(order, response);
		}
	}

	@PostMapping("/placeOrder")
	@Operation(summary = "Place order")
	public ResponseEntity<?> placeOrder(HttpServletRequest req,
			@RequestBody ValidateOtpReuestBody body) {
		User user = userRepo.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		OTP otp = otpRepo.findByUseridAndTypeAndStatus(user,
				OtpType.CONFIRM_ORDER, OtpStatus.INITIATED, new Date());
		boolean validOtp = otpService.validateOTP(user, OtpType.CONFIRM_ORDER,
				OtpStatus.INITIATED, body.getOtp());
		if (validOtp == false) {
			throw new OTPValidationFailed(constants.INVALID_OTP);
		} else {
			List<Cart> items = cartRepo.findByuserId(user);
			Set<Product> allCart = new HashSet<Product>();
			Double total = 0.0;
			for (Cart cart : items) {
				allCart.add(new Product(cart.getProduct_id().getId(),
						cart.getProduct_id().getTitle(),
						cart.getProduct_id().getImageUrl(),
						cart.getProduct_id().getDescription(),
						cart.getQuantity(), cart.getProduct_id().getPrice()));
				total = (total + (cart.getProduct_id().getPrice()
						* cart.getQuantity()));
				Product prod = productRepo
						.getProductById(cart.getProduct_id().getId());
				prod.setQuantity(prod.getQuantity() - cart.getQuantity());
				productRepo.save(prod);
			}
			Orders order = new Orders(user, total, OrderStatus.ORDER_PLACED);
			orderRepo.save(order);
			try {
				Map<String, Object> model = new HashMap();
				model.put("name", user.getName());
				model.put("content", constants.ORDER_CONFIRMED + order.getId() + constants.THANKS_FORSHOPPING);
				emailImpl.sendSimpleMail(new EmailDetails(user.getUsername(),
						constants.ORDER_CONFIRMED_SUBJECT, model));
			} catch (Exception e) {
				logger.info("Error Sending Email: " + e.getMessage());
			}
			for (Product prod : allCart) {
				orderProdRepo.save(
						new OrderAndProducts(order, prod, prod.getQuantity()));
			}
			cartRepo.deleteByuserId(user);
			return new ResponseEntity<placeOrderResponse>(
					new placeOrderResponse(order.getId()), HttpStatus.OK);

		}

	}

	@DeleteMapping("/cancel/{orderId}")
	@Operation(summary = "Cancel Order", description = "Cancel order placed within 24hrs")
	public ResponseEntity<GeneralMessage> cancelOrder(
			@PathVariable(name = "orderId", required = true) int id,HttpServletRequest req ) {
		User user = userRepo.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		Orders order = orderRepo.findById(id).orElseThrow(
				() -> new OrderNotFoundException("OrderId Not Found"));
		if (order != null
				&& order.getOrderDate()
						.after(new Date(System.currentTimeMillis() - 172800000))
				&& order.getStatus() != (OrderStatus.CANCELLED)
				&& order.getStatus() != (OrderStatus.DELIVERED)
				&& order.getStatus() != (OrderStatus.DISPATCHED)
				&& order.getStatus() != (OrderStatus.IN_TRANSIT)) {
			order.setStatus(OrderStatus.CANCELLED);
			orderRepo.save(order);
			try {
				Map<String, Object> model = new HashMap();
				model.put("name", user.getName());
				model.put("content", constants.ORDER_CANCELLED_MAIL + order.getId() +constants.ORDER_CANCELLED_MAIL2 );
				emailImpl.sendSimpleMail(new EmailDetails(user.getUsername(),
						constants.ORDER_CANCELLED_SUBJECT, model));
			} catch (Exception e) {
				logger.info("Error Sending Email: " + e.getMessage());
			}
			return new ResponseEntity<GeneralMessage>(
					new GeneralMessage(constants.ORDER_CANCELLED),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<GeneralMessage>(
					new GeneralMessage(constants.ORDER_CANNOT_BE_CANCELLED),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/update/{orderId}/{status}")
	@Operation(summary = "Update Order Status", description = "Update Order")
	public ResponseEntity<statusUpdatesResPOJO> updateOrderStatus(
			@PathVariable(name = "orderId", required = true) int id,
			@PathVariable(name = "status", required = true) OrderStatus stat,
			HttpServletRequest req ) {
		User user = userRepo.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		Orders order = orderRepo.findById(id).orElseThrow(
				() -> new OrderNotFoundException(constants.ORDER_NOT_FOUND));
		if (order != null) {
			order.setStatus(stat);
			orderRepo.save(order);
			try {
				Map<String, Object> model = new HashMap();
				model.put("name", user.getName());
				model.put("content", constants.ORDER_STATUS_UPDATE + order.getId() +constants.ORDER_STATUS_UPDATE2 );
				emailImpl.sendSimpleMail(new EmailDetails(user.getUsername(),
						constants.ORDER_STATUS_UPDATED_SUBJECT, model));
			} catch (Exception e) {
				logger.info("Error Sending Email: " + e.getMessage());
			}
			return new ResponseEntity<statusUpdatesResPOJO>(
					new statusUpdatesResPOJO(constants.STATUS_UPDATED),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<statusUpdatesResPOJO>(
					new statusUpdatesResPOJO(constants.STATUS_UPDATION_FAILED),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/orderstatus")
	@Operation(summary = "Get Order Status", description = "Get possible status of order")
	public List<OrderStatus> getOrderStatus() {
		List<OrderStatus> resposne = new ArrayList<>();
		OrderStatus values[] = OrderStatus.values();
		for (OrderStatus stat : values) {
			resposne.add(stat);
		}
		return resposne;
	}
}
