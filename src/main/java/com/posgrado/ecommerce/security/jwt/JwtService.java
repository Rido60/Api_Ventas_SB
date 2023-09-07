package com.posgrado.ecommerce.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.service.UserService;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class JwtService {

  //private static final String SECRET_KEY = "mys3cr3tk3y";
  private final UserService userService;

  @Value("${spring.jwt.secret-key}")
  private String secretkey;
  @Value("${spring.jwt.expiration-time}")
  private Long expiredAt;

  public JwtService(UserService userService) {
    this.userService = userService;
  }

  public String create(User user){
    return JWT.create()
        .withSubject(user.getId().toString())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(30)))
        .withClaim("role",user.getRole().getName())
        .sign(Algorithm.HMAC256(secretkey));
  }

  public User decodeToken(String token){
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretkey)).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    String userId = decodedJWT.getSubject();

    return userService.findById(UUID.fromString(userId));

  }

}
