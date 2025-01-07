package com.nigam.springbootexplorer.repository;

import com.nigam.springbootexplorer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBy();
    List<Product> findByTitleContaining(String keyword);
    List<Product> findByQuantityGreaterThan(Integer Quantity);
    List<Product> findPriceLessThanAndQuantityGreaterThan(BigDecimal price, Integer quantity);
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByQuantityDesc();
    List<Product> findTop5ByOrderByPriceDesc();

}
