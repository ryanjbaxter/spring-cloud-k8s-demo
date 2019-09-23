package com.example.chaos.monkey.shopping.gateway.domain;

import java.util.List;
import com.example.chaos.monkey.shopping.domain.Product;

/**
 * @author Benjamin Wilms
 */
public class ProductResponse {

    private ResponseType responseType;
    private List<Product> products;

    public ProductResponse(ResponseType responseType, List<Product> products) {
        this.responseType = responseType;
        this.products = products;
    }

    public ProductResponse() {}

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public List<Product> getProducts() {
        return products;
    }
}
