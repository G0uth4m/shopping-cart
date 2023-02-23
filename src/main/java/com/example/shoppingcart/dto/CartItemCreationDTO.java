package com.example.shoppingcart.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemCreationDTO {

  @NotNull(message = "Product id field is required")
  private Long productId;

  @Positive(message = "Quantity cannot be negative or 0")
  private Integer quantity;
}
