package com.example.chaos.monkey.shopping.bestseller.fashion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BestsellerFashionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestsellerFashionApplication.class, args);
	}
}
