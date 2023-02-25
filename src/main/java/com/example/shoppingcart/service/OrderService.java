package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.OrderDTO;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.entity.OrderItem;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.repository.OrderRepository;
import com.example.shoppingcart.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final CartService cartService;
  private final ModelMapper mapper;
  private final CustomerService customerService;

  @Transactional
  public OrderDTO createOrder(Long customerId) {
    Customer customer = customerService.getCustomerDAO(customerId);
    LocalDateTime now = DateUtil.now();
    Order order = Order.builder().customer(customer).createdAt(now).lastModifiedAt(now).build();

    List<CartItem> cartItems = cartService.getAllCartItems(customerId);
    Set<OrderItem> orderItems = cartItems.stream().map(cartItem ->
        OrderItem.builder()
            .order(order)
            .product(cartItem.getCartItemKey().getProduct())
            .quantity(cartItem.getQuantity()).build()
    ).collect(Collectors.toSet());
    order.setOrderItems(orderItems);
    Order savedOrder = orderRepository.save(order);

    return mapper.map(savedOrder, OrderDTO.class);
  }

  public OrderDTO getOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    return mapper.map(order, OrderDTO.class);
  }

  public List<OrderDTO> getAllOrders(Long customerId, Pageable pageable) {
    return orderRepository.findAllByCustomerId(customerId, pageable).stream()
        .map(order -> mapper.map(order, OrderDTO.class)).collect(Collectors.toList());
  }

}
