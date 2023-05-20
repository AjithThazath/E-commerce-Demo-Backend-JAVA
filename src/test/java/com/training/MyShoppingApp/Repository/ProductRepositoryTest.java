/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.training.MyShoppingApp.POJO.Admin.GetAllProductsResPOJO;
import com.training.MyShoppingApp.entity.Product;

@DataJpaTest
@Import(ProductRepository.class) // is added as product repo does not extend
									// JpaRepoInterface
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository prodRepo;

	@Test
	public void productRepository_save_notNull() {

		Product product1 = new Product("test prod 1", "www.goo.com",
				new StringBuilder("good test prod"), 256.0, 2);

		prodRepo.save(product1);
		GetAllProductsResPOJO all = prodRepo.getAllProduct(2, 4);

		Assertions.assertThat(all).isNotNull();

	}

}
