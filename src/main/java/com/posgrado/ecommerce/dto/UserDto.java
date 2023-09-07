package com.posgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class UserDto {

  private UUID id;
  //@NotBlank(message = "Firstame can not be blank")
  //@Size(min = 3,max = 20, message = "Firstname can is between 3 and 20 characters")
  private String firstname;
  //@NotBlank(message = "Lastame can not be blank")
  //@Size(min = 3,max = 30, message = "Lastname can is between 3 and 30 characters")
  private String lastname;
  //@Email
  private String email;
  //@NotBlank(message = "Firstame can not be blank")
  private String address;
  //@NonNull
  private String roleName;
  //private UUID roleId;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;


}
