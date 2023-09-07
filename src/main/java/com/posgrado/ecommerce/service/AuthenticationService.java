package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.AuthenticationRequest;
import com.posgrado.ecommerce.dto.AuthenticationResponse;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

  private AuthenticationManager authenticationManager;
  private JwtService jwtService;
  public AuthenticationResponse authenticate(AuthenticationRequest request){

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    Authentication authResponse = authenticationManager.authenticate(auth);

    User user = (User) authResponse.getPrincipal();
    String accessToken = jwtService.create(user);

    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .build();

  }
}
