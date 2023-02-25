package com.example.shoppingcart.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
  private Long orderId;
  private Long customerId;
  private Set<OrderItemDTO> orderItems;
  private LocalDateTime createdAt;
  private LocalDateTime lastModifiedAt;
}
