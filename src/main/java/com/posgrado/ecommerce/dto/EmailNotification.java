package com.posgrado.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailNotification {
  @NotBlank(message = "To can not be blank")
  String to;
  @NotBlank(message = "Subjet can not be blank")
  String subject;
  String body;
  boolean hasTemplate;

}
