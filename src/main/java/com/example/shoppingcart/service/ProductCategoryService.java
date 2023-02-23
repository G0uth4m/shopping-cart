package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.ProductCategoryCreationDTO;
import com.example.shoppingcart.dto.ProductCategoryDTO;
import com.example.shoppingcart.dto.ProductCategoryUpdationDTO;
import com.example.shoppingcart.entity.ProductCategory;
import com.example.shoppingcart.exception.DuplicateResourceException;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.repository.ProductCategoryRepository;
import com.example.shoppingcart.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

  private final ModelMapper mapper;
  private final ProductCategoryRepository productCategoryRepository;

  public ProductCategoryDTO createProductCategory(
      ProductCategoryCreationDTO productCategoryCreationDTO) {
    if (productCategoryRepository.existsByName(
        productCategoryCreationDTO.getName())) {
      throw new DuplicateResourceException("Category already exists");
    }
    LocalDateTime now = DateUtil.now();
    ProductCategory productCategory = mapper.map(productCategoryCreationDTO, ProductCategory.class);
    productCategory.setCreatedAt(now);
    productCategory.setLastModifiedAt(now);
    productCategory = productCategoryRepository.save(productCategory);
    return mapper.map(productCategory, ProductCategoryDTO.class);
  }

  public ProductCategoryDTO editProductCategory(String name,
      ProductCategoryUpdationDTO productCategoryUpdationDTO) {
    ProductCategory productCategory = productCategoryRepository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException("Product Category not found"));
    productCategory.setDescription(productCategoryUpdationDTO.getDescription());
    productCategory.setImageUrl(productCategoryUpdationDTO.getImageUrl());
    productCategory.setLastModifiedAt(DateUtil.now());
    productCategory = productCategoryRepository.save(productCategory);
    return mapper.map(productCategory, ProductCategoryDTO.class);
  }

  public ProductCategoryDTO getProductCategory(String name) {
    ProductCategory productCategory = productCategoryRepository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException("Product Category not found"));
    return mapper.map(productCategory, ProductCategoryDTO.class);
  }

  public List<ProductCategoryDTO> getProductCategories(Pageable pageable) {
    return productCategoryRepository.findAll(pageable).stream()
        .map(productCategory -> mapper.map(productCategory, ProductCategoryDTO.class))
        .collect(Collectors.toList());
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteProductCategory(String name) {
    if (!productCategoryRepository.existsByName(name)) {
      throw new ResourceNotFoundException("Product category not found");
    }
    productCategoryRepository.deleteByName(name);
  }

  public ProductCategory getProductCategoryDAO(String name) {
    return productCategoryRepository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException("Product Category not found"));
  }
}
