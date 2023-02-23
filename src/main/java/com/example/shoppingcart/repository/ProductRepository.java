package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findAllByProductCategory_Name(String categoryName, Pageable pageable);
}
