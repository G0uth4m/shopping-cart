package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("select o from Order o where o.customer.customerId = :customerId")
  Page<Order> findAllByCustomerId(Long customerId, Pageable pageable);

}
