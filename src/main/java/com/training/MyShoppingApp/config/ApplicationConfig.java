/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.training.MyShoppingApp.Repository.UserRepository;

@Configuration
public class ApplicationConfig {

	@Autowired
	private UserRepository repository;

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> repository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// public JavaMailSender getJavaMailSender() {
	// JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	// mailSender.setHost("smtp.gmail.com");
	// mailSender.setPort(587);
	//
	// mailSender.setUsername("myshoppingapp874@gmail.com");
	// mailSender.setPassword("bdujdgkdslcugirg");
	//
	// Properties props = mailSender.getJavaMailProperties();
	// props.put("mail.transport.protocol", "smtp");
	// props.put("mail.smtp.auth", "true");
	// props.put("mail.smtp.starttls.enable", "true");
	// props.put("mail.debug", "true");
	//
	// return mailSender;
	// }

}
