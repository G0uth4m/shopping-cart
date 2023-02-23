package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.CartItemCreationDTO;
import com.example.shoppingcart.dto.CartItemDTO;
import com.example.shoppingcart.dto.CartItemUpdationDTO;
import com.example.shoppingcart.service.CartService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/cart/{customerId}/items")
public class CartController {

  private final CartService cartService;

  @GetMapping
  public List<CartItemDTO> getCartItems(@PathVariable Long customerId,
      @ParameterObject Pageable pageable) {
    return cartService.getCustomerCartItems(customerId, pageable);
  }

  @PostMapping
  public CartItemDTO addCartItem(@PathVariable Long customerId,
      @RequestBody @Valid CartItemCreationDTO cartItemCreationDTO) {
    return cartService.addCartItem(customerId, cartItemCreationDTO);
  }

  @PutMapping("/{productId}")
  public CartItemDTO updateCartItem(@PathVariable Long customerId, @PathVariable Long productId,
      @RequestBody @Valid CartItemUpdationDTO cartItemUpdationDTO) {
    return cartService.updateCartItem(customerId, productId, cartItemUpdationDTO);
  }

  @GetMapping("/{productId}")
  public CartItemDTO getCartItem(@PathVariable Long customerId, @PathVariable Long productId) {
    return cartService.getCustomerCartItem(customerId, productId);
  }

  @DeleteMapping("/{productId}")
  public void deleteCartItem(@PathVariable Long customerId, @PathVariable Long productId) {
    cartService.deleteCartItem(customerId, productId);
  }

}
