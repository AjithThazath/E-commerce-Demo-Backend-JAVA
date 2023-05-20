/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		http.cors().and().csrf().disable().authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers("/swagger-ui/*", "/v3/api-docs/**",
					"/auth/**", "/error", "/uploads/**", "/shop/products")
					.permitAll();
			authConfig.anyRequest().authenticated();
		}).authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter,
						UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	// @Bean
	// public ServerAuthenticationEntryPoint jwtAuthEntryPoint() {
	// return (request, authException) -> {
	// return Mono.error(new HttpServerErrorException(HttpStatus.UNAUTHORIZED));
	// };
	// }
}
