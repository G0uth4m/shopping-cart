package com.example.shoppingcart.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductDTO {
  private Long productId;
  private String name;
  private String description;
  private Integer price;
  private String imageUrl;
  private String category;
  private LocalDateTime createdAt;
  private LocalDateTime lastModifiedAt;
}
