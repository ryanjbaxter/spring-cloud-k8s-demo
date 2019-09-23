package com.example.chaos.monkey.shopping.gateway.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Benjamin Wilms
 */
@JsonPropertyOrder({ "fashionResponse", "toysResponse", "hotDealsResponse", "duration", "statusFashion","statusToys","statusHotDeals" })
public class Startpage {

    private long duration;
    private String statusFashion;
    private String statusToys;
    private String statusHotDeals;
    private ProductResponse fashionResponse;
    private ProductResponse toysResponse;
    private ProductResponse hotDealsResponse;

    public void setFashionResponse(ProductResponse fashionResponse) {
        this.setStatusFashion(fashionResponse.getResponseType().name());
        this.fashionResponse = fashionResponse;
    }

    public void setToysResponse(ProductResponse toysResponse) {
        this.setStatusToys(toysResponse.getResponseType().name());
        this.toysResponse = toysResponse;
    }

    public void setHotDealsResponse(ProductResponse hotDealsResponse) {
        this.setStatusHotDeals(hotDealsResponse.getResponseType().name());
        this.hotDealsResponse = hotDealsResponse;
    }

    public void setStatusFashion(String status) {
        this.statusFashion = status;
    }

    public void setStatusToys(String statusToys) {
        this.statusToys = statusToys;
    }

    public void setStatusHotDeals(String status) {
        this.statusHotDeals = status;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatusFashion() {
        return statusFashion;
    }

    public String getStatusToys() {
        return statusToys;
    }

    public String getStatusHotDeals() {
        return statusHotDeals;
    }

    public ProductResponse getFashionResponse() {
        return fashionResponse;
    }

    public ProductResponse getToysResponse() {
        return toysResponse;
    }

    public ProductResponse getHotDealsResponse() {
        return hotDealsResponse;
    }
}
