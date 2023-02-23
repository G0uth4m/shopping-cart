package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.ProductCreationDTO;
import com.example.shoppingcart.dto.ProductDTO;
import com.example.shoppingcart.dto.ProductUpdationDTO;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.entity.ProductCategory;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductCategoryService productCategoryService;
  private final ModelMapper mapper;

  public ProductDTO createProduct(ProductCreationDTO productCreationDTO) {
    ProductCategory category = productCategoryService.getProductCategoryDAO(
        productCreationDTO.getCategory());
    Product product = mapper.map(productCreationDTO, Product.class);
    product.setProductCategory(category);
    LocalDateTime now = DateUtil.now();
    product.setCreatedAt(now);
    product.setLastModifiedAt(now);
    product = productRepository.save(product);
    return mapper.map(product, ProductDTO.class);
  }

  public ProductDTO editProduct(Long productId, ProductUpdationDTO productUpdationDTO) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    ProductCategory category = productCategoryService.getProductCategoryDAO(
        productUpdationDTO.getCategory());
    product.setName(productUpdationDTO.getName());
    product.setDescription(productUpdationDTO.getDescription());
    product.setPrice(productUpdationDTO.getPrice());
    product.setImageUrl(productUpdationDTO.getImageUrl());
    product.setProductCategory(category);
    product.setLastModifiedAt(DateUtil.now());
    product = productRepository.save(product);
    return mapper.map(product, ProductDTO.class);
  }

  public ProductDTO getProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    return mapper.map(product, ProductDTO.class);
  }

  public List<ProductDTO> getProducts(Pageable pageable) {
    return productRepository.findAll(pageable).stream()
        .map(product -> mapper.map(product, ProductDTO.class)).collect(Collectors.toList());
  }

  public void deleteProduct(Long productId) {
    if (!productRepository.existsById(productId)) {
      throw new ResourceNotFoundException("Product not found");
    }
    productRepository.deleteById(productId);
  }

  public List<ProductDTO> getProductsByCategory(String categoryName, Pageable pageable) {
    return productRepository.findAllByProductCategory_Name(categoryName, pageable).stream()
        .map(product -> mapper.map(product, ProductDTO.class)).collect(Collectors.toList());
  }

  public Product getProductDAO(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
  }

}
