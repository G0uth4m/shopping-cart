package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.ProductCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
  Boolean existsByName(String name);
  Optional<ProductCategory> findByName(String name);
  void deleteByName(String name);
}
