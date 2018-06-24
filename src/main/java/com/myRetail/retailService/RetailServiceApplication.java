package com.myRetail.retailService;

import com.myRetail.retailService.mongo.entity.Product;
import com.myRetail.retailService.mongo.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RetailServiceApplication {

	@Autowired
	private ProductInfoRepository repository;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RetailServiceApplication.class, args);

	}

	@PostConstruct
	public void storeSampleData() {
		Map<String, String> map = new HashMap<>();
		map.put("value", "13.49");
		map.put("currency_code", "USD");
		repository.save(new Product("13860428", "The Big Lebowski (Blu-ray) (Widescreen)", map));

		map.put("value", "500");
		map.put("currency_code", "USD");
		repository.save(new Product("15117729", "Apple IPAD", map));
	}
}
