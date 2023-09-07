package com.posgrado.ecommerce.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

  @NotBlank(message = "Name cna not be blank")
  @Size(min = 3,max = 70, message = "Name can is between 3 and 70 characters")
  private String name;
  @NotBlank(message = "Description cna not be blank")
  private String description;
  @URL
  private String imageUrl;
  @DecimalMin(value = "0.0", inclusive = false,message = "The price can not be negative")
  private double price;
  @Min(value = 0,message = "The stock can not be negative")
  @Max(value = 1000)
  private int stock;
  @NonNull
  private boolean active;
  @NonNull
  private UUID categoryId;
}
