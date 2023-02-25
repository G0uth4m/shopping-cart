package com.example.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
  private Long productId;
  private String name;
  private String description;
  private Integer price;
  private String imageUrl;
  private String category;
  private Integer quantity;
}
