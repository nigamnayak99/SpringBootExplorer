package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
}
