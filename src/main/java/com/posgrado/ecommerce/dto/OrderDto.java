package com.posgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.posgrado.ecommerce.entity.OrderItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
  @NotBlank(message = "Comment can not be blank")
  @Size(min = 2,max = 70, message = "Name can is between 2 and 70 characters")
  private String comment;

  @JsonProperty(access = Access.READ_ONLY)
  private double totalPrice;

  List<OrderItemDto> items;

}
