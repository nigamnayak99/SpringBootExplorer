package com.nigam.springbootexplorer.controller;

import com.nigam.springbootexplorer.entity.Product;
import com.nigam.springbootexplorer.rest.RestResponse;
import com.nigam.springbootexplorer.services.ProductService;
import com.nigam.springbootexplorer.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public RestResponse findAll() {
        RestResponse restResponse = Utility.initializeRestResponse(null);
        List<Product> allProducts = productService.findAll();
        restResponse.setResponseData(allProducts);
        restResponse.getMetaData().setSuccessOutCome();
        return restResponse;
    }
}
