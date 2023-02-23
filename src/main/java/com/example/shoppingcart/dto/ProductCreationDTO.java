package com.example.shoppingcart.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCreationDTO {

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  @NotBlank(message = "Price cannot be blank")
  private Integer price;

  private String imageUrl;

  @NotBlank(message = "Category cannot be blank")
  private String category;
}
