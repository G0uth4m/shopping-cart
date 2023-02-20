package com.example.shoppingcart.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCategoryUpdationDTO {

  @NotBlank(message = "Category description cannot be empty")
  private String description;

  private String imageUrl;

}
