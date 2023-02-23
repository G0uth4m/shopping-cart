package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.ProductCreationDTO;
import com.example.shoppingcart.dto.ProductDTO;
import com.example.shoppingcart.dto.ProductUpdationDTO;
import com.example.shoppingcart.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ProductDTO createProduct(@RequestBody @Valid ProductCreationDTO productCreationDTO) {
    return productService.createProduct(productCreationDTO);
  }

  @PutMapping("/{productId}")
  public ProductDTO editProduct(@PathVariable Long productId,
      @RequestBody @Valid ProductUpdationDTO productUpdationDTO) {
    return productService.editProduct(productId, productUpdationDTO);
  }

  @GetMapping("/{productId}")
  public ProductDTO getProduct(@PathVariable Long productId) {
    return productService.getProduct(productId);
  }

  @GetMapping
  public List<ProductDTO> getProducts(@ParameterObject Pageable pageable) {
    return productService.getProducts(pageable);
  }

  @DeleteMapping("/{productId}")
  public void deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
  }

}
