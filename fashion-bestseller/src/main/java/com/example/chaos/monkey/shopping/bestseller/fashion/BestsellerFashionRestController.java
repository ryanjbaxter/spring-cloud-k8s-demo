package com.example.chaos.monkey.shopping.bestseller.fashion;

import com.example.chaos.monkey.shopping.domain.Product;
import com.example.chaos.monkey.shopping.domain.ProductBuilder;
import com.example.chaos.monkey.shopping.domain.ProductCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;

/**
 * @author Benjamin Wilms
 */
@RestController
@RefreshScope
@RequestMapping("/fashion")
public class BestsellerFashionRestController {
    @Value("${products:shorts,pants,shirt,tie,shoes}")
    private String[] products;

    @GetMapping("/bestseller")
    public List<Product> getBestsellerProducts() {
        AtomicLong aLong = new AtomicLong(4);

        ProductBuilder productBuilder = new ProductBuilder();


        List<Product> processed = new ArrayList<>();
        for (String s : products) {
                processed.add(productBuilder.setCategory(ProductCategory.FASHION).setId(aLong.getAndIncrement()).setName(s)
                        .createProduct());
        }

        return processed;
    }

}
