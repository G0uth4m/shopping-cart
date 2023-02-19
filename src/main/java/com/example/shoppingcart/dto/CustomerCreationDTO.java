package com.example.shoppingcart.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerCreationDTO {

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "Password cannot be blank")
  private String password;

  @NotBlank(message = "Email cannot be blank")
  private String email;
}
