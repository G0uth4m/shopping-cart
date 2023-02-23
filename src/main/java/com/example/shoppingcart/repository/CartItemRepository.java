package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.key.CartItemKey;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {

  @Query("select ci from CartItem ci where ci.cartItemKey.customer.customerId = :customerId")
  Page<CartItem> findAllByCustomerId(@Param("customerId") Long customerId, Pageable pageable);

  @Query("select ci from CartItem ci where ci.cartItemKey.customer.customerId = :customerId and ci.cartItemKey.product.productId = :productId")
  Optional<CartItem> findByCustomerIdAndProductId(
      @Param("customerId") Long customerId,
      @Param("productId") Long productId
  );

}
