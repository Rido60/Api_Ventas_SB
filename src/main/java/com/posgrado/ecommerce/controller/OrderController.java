package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.OrderDto;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.exception.response.ErrorResponse;
import com.posgrado.ecommerce.repository.OrderRepository;
import com.posgrado.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "ORDER")
@AllArgsConstructor
@RestController
@RequestMapping("orders")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

  private OrderService orderService;
  private OrderRepository orderRepository;
  @Operation(
      summary = "Save new Order"
  )
  @PostMapping
  private ResponseEntity<String> save(@RequestBody @Valid OrderDto dto) {
    String message = orderService.save(dto);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(message);
  }
 /*//EL CODIGO FUE CON FINES DE TESTEO
  @GetMapping("/{id}/price/total")
  public String getTotalPrice(@PathVariable UUID id){

    double total = orderRepository.getTotalPriceByOrderId(id.toString());

    //double total2 = orderRepository.getTotalPrice(id); Revisar

    return "Total: QUERY NATIVE: " + total ;
  }*/
   @Operation(
       summary = "Get Order by Id",
       responses = {
           @ApiResponse(
               responseCode = "200",
               description = "Order found",
               content = @Content(
                   mediaType = "aplication/json",
                   schema = @Schema(implementation = OrderDto.class)
               )
           ),
           @ApiResponse(
               responseCode = "404",
               description = "Order not found",
               content = @Content(
                   mediaType = "aplication/json",
                   schema = @Schema(implementation = ErrorResponse.class)
               )
           )
       }
   )
  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> getById(
      @Parameter(description = "Id of Order to be searched")
      @PathVariable UUID id){
    OrderDto orderDto = orderService.getById(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(orderDto);
  }
}
