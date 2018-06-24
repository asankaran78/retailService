package com.myRetail.retailService.controller;

import com.myRetail.retailService.exception.NotFoundException;
import com.myRetail.retailService.model.ProductResponse;
import com.myRetail.retailService.mongo.entity.Product;
import com.myRetail.retailService.mongo.repository.ProductInfoRepository;
import com.myRetail.retailService.service.RetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RetailServiceControllerTest {
    @Mock
    private RetailService service;

    @Mock
    private ProductInfoRepository productInfoRepository;

    private RetailServiceController controller;


    @Before
    public void setup() {
        controller = new RetailServiceController(service,productInfoRepository);
    }

    @Test
    public void getProductDetails()  {

        ProductResponse productResponse = new ProductResponse();
        Map<String,String> productInfo =  new HashMap<String, String>() ;

        productInfo.put("value","123");
        productInfo.put("currency_code","GBP");

        productResponse.setName("Test");
        productResponse.setProductId("1234");
        productResponse.setCurrentPrice(productInfo);

        Mockito.when(service.GetProductDetails(Mockito.any())).thenReturn(productResponse);

        ResponseEntity<?> responseEntity = controller.getProductInfo("123");
        assertEquals(responseEntity.getStatusCode().value(),200);

    }

    @Test
    public void updateProductDetails()  {

        Map<String,String> productInfo =  new HashMap<String, String>() ;

        productInfo.put("value","123");
        productInfo.put("currency_code","GBP");
        Product product = new Product("123","test",productInfo);


        Mockito.when(productInfoRepository.findByProductId("123")).thenReturn(product);

        String response = controller.modifyPrice(product,"123");
        assert(response.contains("success"));

    }

    @Test(expected = NotFoundException.class)
    public void unknownProductUpdate()  {

        Map<String,String> productInfo =  new HashMap<String, String>() ;

        productInfo.put("value","123");
        productInfo.put("currency_code","GBP");
        Product product = new Product("123","test",productInfo);


        Mockito.when(productInfoRepository.findByProductId("123")).thenReturn(null);

        String response = controller.modifyPrice(product,"123");

    }
}
