package com.myRetail.retailService.mongo.repository;

import com.myRetail.retailService.mongo.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductInfoRepository extends MongoRepository<Product,String> {
        Product findByProductId(String Id);
}
