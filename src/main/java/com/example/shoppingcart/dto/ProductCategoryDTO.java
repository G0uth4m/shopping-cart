package com.example.shoppingcart.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductCategoryDTO {
  private Long categoryId;
  private String name;
  private String description;
  private String imageUrl;
  private LocalDateTime createdAt;
  private LocalDateTime lastModifiedAt;
}
