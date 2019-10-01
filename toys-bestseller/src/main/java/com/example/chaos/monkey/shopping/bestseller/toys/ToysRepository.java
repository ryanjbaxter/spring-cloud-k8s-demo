package com.example.chaos.monkey.shopping.bestseller.toys;

import com.example.chaos.monkey.shopping.domain.Product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Ryan Baxter
 */
@RepositoryRestResource(path = "/toys")
public interface ToysRepository extends PagingAndSortingRepository<Product, Long> {
}
