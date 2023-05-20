/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.MyShoppingApp.Repository.ProductRepository;
import com.training.MyShoppingApp.entity.Product;

@WebMvcTest(controllers = AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@MockBean
	ProductRepository prodService;

	@BeforeEach
	public void init() {
		Product product = new Product("test prod 1", "www.goo.com",
				new StringBuilder("good test prod"), 256.0, 2);
	}

	// @Test
	// public void AdminController_addProduct_returnAddedProduct()
	// throws Exception {
	// Product product = new Product("test prod 1", "www.goo.com",
	// "good test prod", 256.0, 2);
	// given(prodService.save(ArgumentMatchers.any()))
	// .willAnswer((invocation -> invocation.getArgument(0)));
	//
	// ResultActions response = mockMvc
	// .perform(MockMvcRequestBuilders.post("/admin/add-product")
	// .contentType(MediaType.APPLICATION_JSON)
	// .content(objectMapper.writeValueAsString(product)));
	// response.andExpect(MockMvcResultMatchers.status().isCreated());
	//
	// }

}
