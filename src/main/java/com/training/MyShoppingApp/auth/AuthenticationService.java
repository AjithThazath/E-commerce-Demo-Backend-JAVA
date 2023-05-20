/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.auth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.MyShoppingApp.ExceptionHandler.UserAlreadyExistsException;
import com.training.MyShoppingApp.Repository.UserRepository;
import com.training.MyShoppingApp.config.JwtService;
import com.training.MyShoppingApp.constants.constants;
import com.training.MyShoppingApp.email.EmailDetails;
import com.training.MyShoppingApp.email.EmailServiceImpl;
import com.training.MyShoppingApp.entity.User;
import com.training.MyShoppingApp.enums.Role;

@Service
public class AuthenticationService {

	private Logger logger = LoggerFactory
			.getLogger(AuthenticationService.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authmanager;

	public AuthResponse register(RegisterBody req) {
		boolean userExists = repository.findByUsername(req.getUsername())
				.isPresent();
		if (!userExists) {
			var user = new User(req.getUsername(), req.getName(),
					passwordEncoder.encode(req.getPassword()),
					req.getMobile_number(), Role.USER, req.getAddress(),
					req.getSex());
			repository.save(user);
			var jwtToken = jwtService.generateToken(user);
			Map model = new HashMap<String, Object>();
			model.put("name", req.getName());
			model.put("content", constants.WELCOME_MESSAGE);
			try {
				EmailDetails emailDetails = new EmailDetails(req.getUsername(),
						constants.WELCOME_SUBJECT, model);
				emailService.sendSimpleMail(emailDetails);
			} catch (Exception e) {
				logger.info("Error Sending Email: " + e.getMessage());
			}
			return new AuthResponse(jwtToken);
		} else {
			throw new UserAlreadyExistsException(
					constants.USER_ID_ALREADY_REGISTERED);
		}

	}

	public AuthResponse autenticate(loginBody req) {
		authmanager.authenticate(new UsernamePasswordAuthenticationToken(
				req.getUsername(), req.getPassword()));
		var user = repository.findByUsername(req.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return new AuthResponse(jwtToken);
	}

}
