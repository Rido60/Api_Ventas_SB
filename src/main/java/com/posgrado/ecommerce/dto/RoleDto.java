package com.posgrado.ecommerce.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
  private UUID id;
  private String name;
  private String description;

}
