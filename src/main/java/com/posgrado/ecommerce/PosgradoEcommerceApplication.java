package com.posgrado.ecommerce;

import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.repository.CategoryRepository;
import com.posgrado.ecommerce.repository.ProductRepository;
import com.posgrado.ecommerce.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PosgradoEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosgradoEcommerceApplication.class, args);
	}
	/*@Bean
	public CommandLineRunner setData (
			CategoryRepository categoryRepository,
			ProductRepository productRepository,
			RoleRepository rolyRepository
	){
		return args -> {

			Category category = new Category();
			category.setName("TRAVEL");
			category.setDescription("Mochilas Viajeras");

			Category category1 = new Category();
			category1.setName("SPORT");
			category1.setDescription("Mochilas para futbolistas");

			categoryRepository.save(category);
			categoryRepository.save(category1);

			Product product = new Product();
			product.setName("Producto Z");
			product.setDescription("Descripcion producto z");
			product.setStock(100);
			product.setPrice(195.99);
			product.setImageUrl("http://image.jpg");
			product.setActive(true);
			product.setCategory(category1);

			productRepository.save(product);

			Role role = new Role();
			role.setName("USER");
			role.setDescription("This is a User");

			Role role1 = new Role();
			role1.setName("ADMIN");
			role1.setDescription("This is a Admin");

			rolyRepository.save(role);
			rolyRepository.save(role1);


		};
	}*/


}
