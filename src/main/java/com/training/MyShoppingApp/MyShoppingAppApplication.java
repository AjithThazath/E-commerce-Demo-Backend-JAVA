package com.training.MyShoppingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import com.training.MyShoppingApp.Properties.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileUploadProperties.class})
@EnableAsync(proxyTargetClass = true)
public class MyShoppingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyShoppingAppApplication.class, args);
	}

}
