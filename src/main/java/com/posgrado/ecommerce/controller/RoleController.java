package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.OrderDto;
import com.posgrado.ecommerce.dto.UserDto;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "ROLE")
@AllArgsConstructor
@RestController
@RequestMapping("roles")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

  private RoleService roleService;
  @Operation(
      summary = "Get Role by Name"
  )
  @GetMapping("/name/{name}")
  public ResponseEntity<Role> getByName(@PathVariable String name){
    Role roleFound = roleService.getByName(name);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(roleFound);
  }

  @Operation(
      summary = "Save new Role"
  )
  @PostMapping
  private ResponseEntity<Role> save (@RequestBody Role role){

    Role message = roleService.saveRole(role);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(message);
  }

}
