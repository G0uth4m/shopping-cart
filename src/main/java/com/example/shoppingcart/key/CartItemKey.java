package com.example.shoppingcart.key;

import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Product;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CartItemKey implements Serializable {

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false, updatable = false)
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;
}
