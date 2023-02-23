package com.example.shoppingcart.dto;

import lombok.Data;

@Data
public class CartItemDTO {
  private Long productId;
  private String name;
  private String description;
  private Integer price;
  private String imageUrl;
  private String category;
  private Integer quantity;
}
