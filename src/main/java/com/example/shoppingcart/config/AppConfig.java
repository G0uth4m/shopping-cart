package com.example.shoppingcart.config;

import com.example.shoppingcart.dto.CartItemDTO;
import com.example.shoppingcart.dto.OrderDTO;
import com.example.shoppingcart.dto.OrderItemDTO;
import com.example.shoppingcart.dto.ProductDTO;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.entity.OrderItem;
import com.example.shoppingcart.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    TypeMap<Product, ProductDTO> productDTOTypeMap = modelMapper.createTypeMap(Product.class,
        ProductDTO.class);
    productDTOTypeMap.addMappings(mapper ->
        mapper.map(product -> product.getProductCategory().getName(), ProductDTO::setCategory));

    TypeMap<CartItem, CartItemDTO> cartItemDTOTypeMap = modelMapper.createTypeMap(CartItem.class,
        CartItemDTO.class);
    cartItemDTOTypeMap.addMappings(mapper -> {
      mapper.map(cartItem -> cartItem.getCartItemKey().getProduct().getProductId(),
          CartItemDTO::setProductId);
      mapper.map(cartItem -> cartItem.getCartItemKey().getProduct().getName(),
          CartItemDTO::setName);
      mapper.map(cartItem -> cartItem.getCartItemKey().getProduct().getDescription(),
          CartItemDTO::setDescription);
      mapper.map(cartItem -> cartItem.getCartItemKey().getProduct().getPrice(),
          CartItemDTO::setPrice);
      mapper.map(cartItem -> cartItem.getCartItemKey().getProduct().getImageUrl(),
          CartItemDTO::setImageUrl);
      mapper.map(cartItem -> cartItem.getCartItemKey().getProduct().getProductCategory().getName(),
          CartItemDTO::setCategory);
    });

    TypeMap<Order, OrderDTO> orderDTOTypeMap = modelMapper.createTypeMap(Order.class,
        OrderDTO.class);
    orderDTOTypeMap.addMappings(mapper -> mapper.map(order -> order.getCustomer().getCustomerId(),
        OrderDTO::setCustomerId));

    TypeMap<OrderItem, OrderItemDTO> orderItemDTOTypeMap = modelMapper.createTypeMap(
        OrderItem.class, OrderItemDTO.class);
    orderItemDTOTypeMap.addMappings(mapper -> {
      mapper.map(orderItem -> orderItem.getProduct().getProductId(), OrderItemDTO::setProductId);
      mapper.map(orderItem -> orderItem.getProduct().getName(), OrderItemDTO::setName);
      mapper.map(orderItem -> orderItem.getProduct().getDescription(),
          OrderItemDTO::setDescription);
      mapper.map(orderItem -> orderItem.getProduct().getPrice(), OrderItemDTO::setPrice);
      mapper.map(orderItem -> orderItem.getProduct().getImageUrl(), OrderItemDTO::setImageUrl);
      mapper.map(orderItem -> orderItem.getProduct().getProductCategory().getName(),
          OrderItemDTO::setCategory);
    });

    return modelMapper;
  }

}
