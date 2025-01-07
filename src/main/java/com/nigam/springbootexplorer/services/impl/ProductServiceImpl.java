package com.nigam.springbootexplorer.services.impl;

import com.nigam.springbootexplorer.entity.Product;
import com.nigam.springbootexplorer.repository.ProductRepository;
import com.nigam.springbootexplorer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
