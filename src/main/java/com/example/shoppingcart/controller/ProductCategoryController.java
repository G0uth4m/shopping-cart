package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.ProductCategoryCreationDTO;
import com.example.shoppingcart.dto.ProductCategoryDTO;
import com.example.shoppingcart.dto.ProductCategoryUpdationDTO;
import com.example.shoppingcart.service.ProductCategoryService;
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
@RequestMapping("/v1/api/products/categories")
public class ProductCategoryController {

  private final ProductCategoryService productCategoryService;

  @PostMapping
  public ProductCategoryDTO createProductCategory(
      @RequestBody @Valid ProductCategoryCreationDTO productCategoryCreationDTO) {
    return productCategoryService.createProductCategory(productCategoryCreationDTO);
  }

  @PutMapping("/{categoryName}")
  public ProductCategoryDTO editProductCategory(@PathVariable String categoryName,
      @RequestBody @Valid ProductCategoryUpdationDTO productCategoryUpdationDTO) {
    return productCategoryService.editProductCategory(categoryName, productCategoryUpdationDTO);
  }

  @GetMapping("/{categoryName}")
  public ProductCategoryDTO getProductCategory(@PathVariable String categoryName) {
    return productCategoryService.getProductCategory(categoryName);
  }

  @GetMapping
  public List<ProductCategoryDTO> getProductCategories(@ParameterObject Pageable pageable) {
    return productCategoryService.getProductCategories(pageable);
  }

  @DeleteMapping("/{categoryName}")
  public void deleteCategory(@PathVariable String categoryName) {
    productCategoryService.deleteProductCategory(categoryName);
  }

}
