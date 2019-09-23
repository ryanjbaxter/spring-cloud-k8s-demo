package com.example.chaos.monkey.shopping.bestseller.toys;

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
@RequestMapping("/toys")
public class BestsellerToysRestController {

    @GetMapping("/bestseller")
    public List<Product> getBestsellerProducts() {
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
