package com.example.chaos.monkey.shopping.bestseller.toys;

import com.example.chaos.monkey.shopping.domain.ProductBuilder;
import com.example.chaos.monkey.shopping.domain.ProductCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "com.example.chaos.monkey.shopping.domain")
public class BestsellerToysApplication implements CommandLineRunner {

	@Autowired
	ToysRepository toysRepository;

	public static void main(String[] args) {
		SpringApplication.run(BestsellerToysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProductBuilder productBuilder = new ProductBuilder();

		toysRepository.save(productBuilder.setCategory(ProductCategory.TOYS).setName("LEGO Star Wars Yodas Hut")
				.createProduct());

		toysRepository.save(productBuilder.setCategory(ProductCategory.TOYS).setName("LEGO Star Wars Millennium Falcon")
				.createProduct());

		toysRepository.save(productBuilder.setCategory(ProductCategory.TOYS).setName("LEGO Star Wars Imperial Tie Fighter")
				.createProduct());

	}
}
 