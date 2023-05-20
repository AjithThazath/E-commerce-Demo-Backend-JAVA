/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyShoppingApp.ExceptionHandler.UserNotFoundException;
import com.training.MyShoppingApp.POJO.User.GetUserDetails;
import com.training.MyShoppingApp.POJO.User.PutupdateUserDetails;
import com.training.MyShoppingApp.Repository.UserRepository;
import com.training.MyShoppingApp.entity.User;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("user")
@SecurityRequirement(name = "bearerAuth")
@Transactional
@Tag(name = "User apis", description = "User apis to get and update user details")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/userdetails")
	public GetUserDetails getUserDetails(HttpServletRequest req) {
		User user = userRepo.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		GetUserDetails response = new GetUserDetails(user);
		return response;
	}

	@PutMapping("update/userdetails")
	public GetUserDetails updateUserDeatails(HttpServletRequest req,
			@RequestBody PutupdateUserDetails body) {
		User user = userRepo.findByUsername((String) req.getAttribute("user"))
				.orElseThrow(() -> new UserNotFoundException("Invalid User"));
		user.setAddress(body.getAddress());
		user.setName(body.getName());
		user.setMobile_number(body.getMobile_number());
		user.setSex(body.getSex());
		GetUserDetails response = new GetUserDetails(user);
		return response;
	}

}
