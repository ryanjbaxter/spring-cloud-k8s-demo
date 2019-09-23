package com.example.chaos.monkey.shopping.hotdeals;


import com.example.chaos.monkey.shopping.domain.Product;
import com.example.chaos.monkey.shopping.domain.ProductBuilder;
import com.example.chaos.monkey.shopping.domain.ProductCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Benjamin Wilms
 */
@RestController
public class HotDealsRestController {

    @GetMapping("/hotdeals")
    public List<Product> getHotDeals() {
        AtomicLong aLong = new AtomicLong(7);

        ProductBuilder productBuilder = new ProductBuilder();

        Product product1 = productBuilder.setCategory(ProductCategory.FASHION).setId(aLong.getAndIncrement()).setName("Thermal Winter Warm Hot Heat" +
                " Socks")
                .createProduct();

        Product product2 = productBuilder.setCategory(ProductCategory.TOYS).setId(aLong.getAndIncrement()).setName("RC Quadcopter Drone with 2.0MP Camera Live")
                .createProduct();

        Product product3 = productBuilder.setCategory(ProductCategory.BOOKS).setId(aLong.getAndIncrement()).setName("Spring Boot 2: Moderne Softwareentwicklung mit Spring 5")
                .createProduct();
        return Arrays.asList(product1, product2, product3);
    }

}
