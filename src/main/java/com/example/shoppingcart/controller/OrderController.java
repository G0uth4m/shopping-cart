package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.OrderDTO;
import com.example.shoppingcart.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/customers/{customerId}/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public OrderDTO createOrder(@PathVariable Long customerId) {
    return orderService.createOrder(customerId);
  }

  @GetMapping("/{orderId}")
  public OrderDTO getOrder(@PathVariable Long customerId, @PathVariable Long orderId) {
    return orderService.getOrder(orderId);
  }

  @GetMapping
  public List<OrderDTO> getOrders(@PathVariable Long customerId, @ParameterObject Pageable pageable) {
    return orderService.getAllOrders(customerId, pageable);
  }

}
