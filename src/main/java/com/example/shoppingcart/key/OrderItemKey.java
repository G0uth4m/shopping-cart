package com.example.shoppingcart.key;

import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.entity.Product;
import java.io.Serializable;
import lombok.Data;

@Data
public class OrderItemKey implements Serializable {
  private Order order;
  private Product product;
}
