package com.example.shoppingcart.dto;

import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemUpdationDTO {

  @Positive(message = "Quantity cannot be negative or 0")
  private Integer quantity;

}
