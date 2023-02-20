package com.example.shoppingcart.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCategoryCreationDTO {

  @NotBlank(message = "Category name cannot be blank")
  private String name;

  @NotBlank(message = "Category description cannot be blank")
  private String description;

  private String imageUrl;
}
