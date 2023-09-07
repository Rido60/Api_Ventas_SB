package com.posgrado.ecommerce.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

  private Integer quantity;
  private UUID productId;
}
