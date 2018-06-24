package com.myRetail.retailService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myRetail.retailService.delegate.ProductDetailsDelegate;
import com.myRetail.retailService.exception.NotFoundException;
import com.myRetail.retailService.exception.ProductFatalException;
import com.myRetail.retailService.model.ProductResponse;
import com.myRetail.retailService.mongo.entity.Product;
import com.myRetail.retailService.mongo.repository.ProductInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Map;

@Service
public class RetailService {


    private ProductDetailsDelegate productDetailsDelegate;

    private ProductInfoRepository productInfoRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    public RetailService(ProductDetailsDelegate productDetailsDelegate, ProductInfoRepository productInfoRepository) {
        this.productDetailsDelegate = productDetailsDelegate;
        this.productInfoRepository = productInfoRepository;
    }

    public ProductResponse GetProductDetails(String productId)  {
        ProductResponse productResponse = new ProductResponse();

        try {
            ResponseEntity<String> response = productDetailsDelegate.GetProductDetails(productId);
            String content = response.getBody();
            Map<String, Map> responseMap = mapper.readValue(response.getBody(), Map.class);


            Map<String,Map> productMap = responseMap.get("product");
            Map<String,Map> itemMap = productMap.get("item");
            Map<String,String> prodDescrMap = itemMap.get(("product_description"));

            productResponse.setProductId(productId);
            productResponse.setName(prodDescrMap.get("title"));

            Product product = productInfoRepository.findByProductId(productId);
            if(product == null){
                throw new NotFoundException("Couldn't get Product details from DataStore");
            }else{
                productResponse.setCurrentPrice(product.getCurrentPrice());
            }
        }catch (HttpClientErrorException ex){
            throw new NotFoundException("Couldn't get Product details from external service");

        }catch (IOException ex){
            throw new ProductFatalException("Couldn't get Product details",ex);
        }

        return productResponse;
    }
}
