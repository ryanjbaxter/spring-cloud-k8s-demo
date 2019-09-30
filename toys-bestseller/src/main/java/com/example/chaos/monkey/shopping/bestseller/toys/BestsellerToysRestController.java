package com.example.chaos.monkey.shopping.bestseller.toys;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import com.example.chaos.monkey.shopping.domain.Product;
import com.example.chaos.monkey.shopping.domain.ProductBuilder;
import com.example.chaos.monkey.shopping.domain.ProductCategory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Benjamin Wilms
 */
@RestController
@RequestMapping("/toys")
public class BestsellerToysRestController {

    private String appid = UUID.randomUUID().toString();

    @GetMapping("/bestseller")
    public List<Product> getBestsellerProducts(HttpServletResponse response) {
        response.addHeader("appid", appid);
        AtomicLong aLong = new AtomicLong(1);

        ProductBuilder productBuilder = new ProductBuilder();

        Product product1 = productBuilder.setCategory(ProductCategory.TOYS).setId(aLong.getAndIncrement()).setName("LEGO Star Wars Yodas Hut")
                .createProduct();

        Product product2 = productBuilder.setCategory(ProductCategory.TOYS).setId(aLong.getAndIncrement()).setName("LEGO Star Wars Millennium Falcon")
                .createProduct();

        Product product3 = productBuilder.setCategory(ProductCategory.TOYS).setId(aLong.getAndIncrement()).setName("LEGO Star Wars Imperial Tie Fighter")
                .createProduct();
        return Arrays.asList(product1, product2, product3);
    }

}
