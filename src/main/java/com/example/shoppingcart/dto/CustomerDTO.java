package com.example.shoppingcart.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CustomerDTO {
  private Long customerId;
  private String name;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime lastModifiedAt;
}
