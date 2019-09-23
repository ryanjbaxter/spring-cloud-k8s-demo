package com.example.chaos.monkey.shopping.domain;

public class ProductBuilder {
    private long id;
    private String name;
    private ProductCategory category;

    public ProductBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setCategory(ProductCategory category) {
        this.category = category;
        return this;
    }

    public Product createProduct() {
        return new Product(id, name, category);
    }
}