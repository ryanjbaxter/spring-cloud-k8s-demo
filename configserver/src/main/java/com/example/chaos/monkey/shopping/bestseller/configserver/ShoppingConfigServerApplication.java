package com.example.chaos.monkey.shopping.bestseller.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Ryan Baxter
 */
@EnableConfigServer
@SpringBootApplication
public class ShoppingConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingConfigServerApplication.class, args);
	}
}
