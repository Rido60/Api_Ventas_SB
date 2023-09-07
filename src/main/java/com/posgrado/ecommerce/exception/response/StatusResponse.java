package com.posgrado.ecommerce.exception.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StatusResponse {

  private int status;
  private String error;
  private String message;
}
