package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.CartItemCreationDTO;
import com.example.shoppingcart.dto.CartItemDTO;
import com.example.shoppingcart.dto.CartItemUpdationDTO;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.key.CartItemKey;
import com.example.shoppingcart.repository.CartItemRepository;
import com.example.shoppingcart.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartItemRepository cartItemRepository;
  private final ModelMapper mapper;
  private final CustomerService customerService;
  private final ProductService productService;

  public CartItemDTO addCartItem(Long customerId, CartItemCreationDTO cartItemCreationDTO) {
    Customer customer = customerService.getCustomerDAO(customerId);
    Product product = productService.getProductDAO(cartItemCreationDTO.getProductId());
    LocalDateTime now = DateUtil.now();
    CartItem cartItem = CartItem.builder().cartItemKey(new CartItemKey(customer, product))
        .quantity(cartItemCreationDTO.getQuantity()).createdAt(now).lastModifiedAt(now).build();
    cartItem = cartItemRepository.save(cartItem);
    return mapper.map(cartItem, CartItemDTO.class);
  }

  public List<CartItemDTO> getCustomerCartItems(Long customerId, Pageable pageable) {
    return cartItemRepository.findAllByCustomerId(customerId, pageable).stream()
        .map(cartItem -> mapper.map(cartItem, CartItemDTO.class)).collect(Collectors.toList());
  }

  public CartItemDTO getCustomerCartItem(Long customerId, Long productId) {
    CartItem cartItem = cartItemRepository.findByCustomerIdAndProductId(customerId, productId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart item does not exist"));
    return mapper.map(cartItem, CartItemDTO.class);
  }

  public CartItemDTO updateCartItem(Long customerId, Long productId,
      CartItemUpdationDTO cartItemUpdationDTO) {
    CartItem cartItem = cartItemRepository.findByCustomerIdAndProductId(customerId, productId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart item does not exist"));
    cartItem.setQuantity(cartItemUpdationDTO.getQuantity());
    cartItem.setLastModifiedAt(DateUtil.now());
    cartItem = cartItemRepository.save(cartItem);
    return mapper.map(cartItem, CartItemDTO.class);
  }

  public void deleteCartItem(Long customerId, Long productId) {
    CartItem cartItem = cartItemRepository.findByCustomerIdAndProductId(customerId, productId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart item does not exist"));
    cartItemRepository.delete(cartItem);
  }

}
