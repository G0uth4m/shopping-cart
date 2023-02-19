package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.CustomerCreationDTO;
import com.example.shoppingcart.dto.CustomerDTO;
import com.example.shoppingcart.dto.CustomerUpdationDTO;
import com.example.shoppingcart.service.CustomerService;
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
@RequestMapping("/v1/api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public CustomerDTO createCustomer(@RequestBody @Valid CustomerCreationDTO customerCreationDTO) {
    return customerService.createCustomer(customerCreationDTO);
  }

  @PutMapping("/{customerId}")
  public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody @Valid CustomerUpdationDTO customerUpdationDTO) {
    return customerService.editCustomer(customerId, customerUpdationDTO);
  }

  @GetMapping("/{customerId}")
  public CustomerDTO getCustomer(@PathVariable Long customerId) {
    return customerService.getCustomer(customerId);
  }

  @GetMapping
  public List<CustomerDTO> getCustomers(@ParameterObject Pageable pageable) {
    return customerService.getCustomers(pageable);
  }

  @DeleteMapping("/{customerId}")
  public void deleteCustomer(@PathVariable Long customerId) {
    customerService.deleteCustomer(customerId);
  }
}
