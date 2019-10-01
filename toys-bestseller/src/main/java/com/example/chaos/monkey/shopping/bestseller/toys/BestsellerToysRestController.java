package com.example.chaos.monkey.shopping.bestseller.toys;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import com.example.chaos.monkey.shopping.domain.Product;

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

    private ToysRepository toysRepository;

    public BestsellerToysRestController(ToysRepository toysRepository) {
        this.toysRepository = toysRepository;
    }

    @GetMapping("/bestseller")
    public Iterable<Product> getBestsellerProducts(HttpServletResponse response) {
        response.addHeader("appid", appid);
        AtomicLong aLong = new AtomicLong(1);

        return toysRepository.findAll();
    }

}
