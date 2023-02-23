package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.CustomerCreationDTO;
import com.example.shoppingcart.dto.CustomerDTO;
import com.example.shoppingcart.dto.CustomerUpdationDTO;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.repository.CustomerRepository;
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
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final ModelMapper mapper;

  public CustomerDTO createCustomer(CustomerCreationDTO customerCreationDTO) {
    LocalDateTime now = DateUtil.now();
    Customer customer = mapper.map(customerCreationDTO, Customer.class);
    customer.setCreatedAt(now);
    customer.setLastModifiedAt(now);
    customer = customerRepository.save(customer);
    return mapper.map(customer, CustomerDTO.class);
  }

  public CustomerDTO editCustomer(Long customerId, CustomerUpdationDTO customerUpdationDTO) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    customer.setName(customerUpdationDTO.getName());
    customer.setEmail(customerUpdationDTO.getEmail());
    customer.setLastModifiedAt(DateUtil.now());
    customer = customerRepository.save(customer);
    return mapper.map(customer, CustomerDTO.class);
  }

  public CustomerDTO getCustomer(Long customerId) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    return mapper.map(customer, CustomerDTO.class);
  }

  public List<CustomerDTO> getCustomers(Pageable pageable) {
    return customerRepository.findAll(pageable).stream()
        .map(customer -> mapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
  }

  public void deleteCustomer(Long customerId) {
    if (!customerRepository.existsById(customerId)) {
      throw new ResourceNotFoundException("Customer not found");
    }
    customerRepository.deleteById(customerId);
  }

  public Customer getCustomerDAO(Long customerId) {
    return customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
  }
}
