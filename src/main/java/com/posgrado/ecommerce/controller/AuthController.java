package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.AuthenticationRequest;
import com.posgrado.ecommerce.dto.AuthenticationResponse;
import com.posgrado.ecommerce.dto.EmailNotification;
import com.posgrado.ecommerce.dto.UserDto;
import com.posgrado.ecommerce.service.AuthenticationService;
import com.posgrado.ecommerce.service.EmailService;
import com.posgrado.ecommerce.service.RegistrationService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "AUTHENTICATE")
@RestController
@RequestMapping("auth")
@AllArgsConstructor

public class AuthController {

  private RegistrationService registrationService;
  private EmailService emailService;
  private AuthenticationService authenticationService;
  @Operation(
      summary = "Register new User"
  )
  @PostMapping("/register")
  private ResponseEntity<String> register (@RequestBody @Valid UserDto userDto){
    String message = registrationService.register(userDto);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(message);
  }
  @Hidden
  @PostMapping("/email")
  public String sendEmail (){

    EmailNotification email = EmailNotification.builder()
        .to("jmendezjimenez600@gmail.com")
        .subject("TEST")
        .body("Mensaje en Texto plano ")
        .hasTemplate(false)
        .build();

    EmailNotification email1 = EmailNotification.builder()
        .to("jmendezjimenez600@gmail.com")
        .subject("TEST CON TEMPLATE")
        .body("<h1>Contenido de Email</h1>")
        .hasTemplate(true)
        .build();


    emailService.send(email);
    emailService.send(email1);

    return "Correo enviado exitosamente";
  }
  @Operation(
      summary = "Confirm Token"
  )
  @GetMapping("/confirm")
  public ResponseEntity<String> confirm (@RequestParam String token){
     String message = registrationService.confirm(token);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(message);
  }

  @Operation(
      summary = "identify"
  )
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate (
      @RequestBody @Valid AuthenticationRequest request){
    AuthenticationResponse response = authenticationService.authenticate(request);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);

  }

}
