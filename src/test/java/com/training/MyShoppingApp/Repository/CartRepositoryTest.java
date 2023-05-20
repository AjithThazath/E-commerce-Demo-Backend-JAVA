/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.MyShoppingApp.entity.Cart;
import com.training.MyShoppingApp.entity.Product;
import com.training.MyShoppingApp.entity.User;
import com.training.MyShoppingApp.enums.Role;
import com.training.MyShoppingApp.enums.Sex;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(ProductRepository.class)
public class CartRepositoryTest {

	@Autowired
	CartRepository cartRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ProductRepository prodRepo;

	@Test
	public void cartRepository_findById_ListOfCart() {

		// Arrange
		User user = new User("ajith@test.com", "Ajith", "12345678", 9876543211L,
				Role.ADMIN, "123 st", Sex.MALE);
		System.out.print(user);
		User userSave = userRepo.save(user);
		Product product = new Product("test prod 1", "www.goo.com",
				new StringBuilder("good test prod"), 256.0, 2);
		System.out.print(product);
		prodRepo.save(product);
		Cart cart1 = new Cart(user, product, 1);
		cartRepo.save(cart1);

		// Action
		List<User> result = userRepo.findAll();

		// Assert
		// Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.size()).isEqualTo(1);
	}

}
