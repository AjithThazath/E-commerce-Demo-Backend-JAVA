/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.Repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.MyShoppingApp.ExceptionHandler.ProductNotFoundException;
import com.training.MyShoppingApp.POJO.Admin.GetAllProductsResPOJO;
import com.training.MyShoppingApp.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

//public interface ProductRepository extends JpaRepository<Product, Integer> {

@Repository
@Transactional
public class ProductRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	public Product getProductById(int id) throws ProductNotFoundException {
		Product p = em.find(Product.class, id);
		if (p == null) {
			throw new ProductNotFoundException(
					"Product Not Found.send a valid ID");
		}
		return p;
	}

	public GetAllProductsResPOJO getAllProduct(Integer limit, Integer skip) {
		String query = "";
		if (limit == null || skip == null) {
			query = "select * from product";
		} else {
			query = "select * from product LIMIT " + limit + " OFFSET " + skip;
		}
		Long resultSize = (Long) em
				.createNativeQuery("select count(*) from product")
				.getSingleResult();
		List resultList = em.createNativeQuery(query, Product.class)
				.getResultList();
		return new GetAllProductsResPOJO(resultSize, resultList);
	}

	public Product save(Product newProd) {
		if (newProd.getId() > 0) {
			return em.merge(newProd);
		} else {
			em.persist(newProd);
			return newProd;
		}
	}

	public Product delete(int id) throws ProductNotFoundException {
		Product productToDelete = getProductById(id);
		if (productToDelete == null) {
			throw new ProductNotFoundException(
					"Product Not Found.sent a valid ID");
		}
		em.remove(productToDelete);
		return productToDelete;
	}
}
