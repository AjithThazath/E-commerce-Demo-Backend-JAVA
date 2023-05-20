/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.MyShoppingApp.entity.Product;

@WebMvcTest(controllers = ShopController.class)
@AutoConfigureMockMvc(addFilters = true)
@ExtendWith(MockitoExtension.class)
public class ShopControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Test
	public void ShopController_mainPage_ListProducts() throws Exception {
		List<Product> prods = new ArrayList();
		prods.add(new Product("test prod 1", "www.goo.com",
				new StringBuilder("good test prod"), 256.0, 2));
		prods.add(new Product("test prod 2", "test.com",
				new StringBuilder("good test prod 2"), 256.0, 2));

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders
				.get("/shop/products").contentType(MediaType.APPLICATION_JSON));

		response.andExpect(MockMvcResultMatchers.status().isOk());
		// .andExpect(MockMvcResultMatchers.jsonPath("$.content.size",
		// CoreMatchers.is(response.size())));
	}

}
