package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.UserDto;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.exception.response.ErrorResponse;
import com.posgrado.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "USER")
@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
  private UserService userService;
  @Operation(
      summary = "Get User by Id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "User found",
              content = @Content(
                  mediaType = "aplication/json",
                  schema = @Schema(implementation = User.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "User not found",
              content = @Content(
                  mediaType = "aplication/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  @GetMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<UserDto> getById(
      @Parameter(description = "Id of User to be searched")
      @PathVariable UUID id) {
    UserDto userFound = userService.getById(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(userFound);
  }

}
