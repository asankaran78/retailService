package com.myRetail.retailService.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "productsCollection")
public class Product {


    public String getProductId() {
        return productId;
    }

    @Id
    private String productId;

    private String name;

    private Map<String,String> currentPrice;

    public Product(String productId, String name, Map<String, String> currentPrice) {
        this.productId = productId;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    Product(){
    }

    public void setId(String productId) {
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
