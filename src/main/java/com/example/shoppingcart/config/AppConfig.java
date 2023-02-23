package com.example.shoppingcart.config;

import com.example.shoppingcart.dto.ProductDTO;
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
    return modelMapper;
  }

}
