/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/auth")
@Transactional
public class AuthController {

	@Autowired
	private AuthenticationService authService;

	@PostMapping("/signUp")
	public ResponseEntity<AuthResponse> register(
			@RequestBody RegisterBody req) {
		return ResponseEntity.ok(authService.register(req));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody loginBody req) {
		return ResponseEntity.ok(authService.autenticate(req));
	}

}
