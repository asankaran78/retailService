package com.myRetail.retailService.controller;

import com.myRetail.retailService.exception.NotFoundException;
import com.myRetail.retailService.model.ProductResponse;
import com.myRetail.retailService.mongo.entity.Product;
import com.myRetail.retailService.mongo.repository.ProductInfoRepository;
import com.myRetail.retailService.service.RetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value="MyRetailAPI", description = "Retrieve/Modify Product Information by product id")
@RestController
public class RetailServiceController {


    @Autowired
    private RetailService retailService;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    RetailServiceController(RetailService retailService, ProductInfoRepository productInfoRepository)
    {
        this.retailService = retailService;
        this.productInfoRepository = productInfoRepository;
    }

    @ApiOperation(value = "Gets the product and price information by product id")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success response"),
                    @ApiResponse(code = 404, message = "Product not found")})
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<ProductResponse> getProductInfo(@PathVariable("id") String productId) {

        return new ResponseEntity<>(retailService.GetProductDetails(productId),HttpStatus.OK);
    }

    @ApiOperation(value = "Modifies the product information")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String modifyPrice(@RequestBody Product prodInfo, @PathVariable ("id") String productId)
    {

        if (productInfoRepository.findByProductId(productId)!= null) {
            productInfoRepository.save(prodInfo);
            return "{\"response\":\"success\"}";
        }
        else {
            throw new NotFoundException("ProductId doesn't exist in the DataStore");
        }
    }
}
