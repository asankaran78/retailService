package com.myRetail.retailService.model;

import java.util.Map;

public class ProductResponse {

    private String productId;

    private String name;

    private Map<String,String> currentPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Map<String, String> currentPrice) {
        this.currentPrice = currentPrice;
    }


}
