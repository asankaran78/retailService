package com.myRetail.retailService.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductDetailsDelegate {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${serviceUrl.redSkyServiceUrl}")
    private String redSkyServiceUrl;


    ProductDetailsDelegate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> GetProductDetails(String productId){
            return restTemplate.getForEntity(redSkyServiceUrl, String.class, productId);

    }
}
