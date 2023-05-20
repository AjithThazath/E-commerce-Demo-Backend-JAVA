/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My Shopping App API", version = "${api.version}", contact = @Contact(name = "Ajith Thazath", email = "ajith.nair1994@gmail.com", url = "https://www.MyshoppingApp.com"), license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), termsOfService = "${tos.uri}", description = "${api.description}"), servers = @Server(url = "${api.server.url}", description = "Development"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenAPISecurityConfiguration {

	// global config for JWT auth Swagger

	// @Bean
	// public OpenAPI customizeOpenAPI() {
	// final String securitySchemeName = "bearerAuth";
	// return new OpenAPI()
	// .addSecurityItem(
	// new SecurityRequirement().addList(securitySchemeName))
	// .components(
	// new Components().addSecuritySchemes(securitySchemeName,
	// new SecurityScheme().name(securitySchemeName)
	// .type(SecurityScheme.Type.HTTP)
	// .scheme("bearer").bearerFormat("JWT")));
	// }

}
