package com.posgrado.ecommerce.security;

import com.posgrado.ecommerce.security.jwt.JwtAuthenticationFilter;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

  private JwtAuthenticationFilter jwtAuthenticationFilter;

  private static final String[] SWAGGER_WHITE_LIST ={
      "/v3/api-docs/**",
      "/swagger-ui/**",
      "/swagger-ui.html",
      "/swagger-resources/**",
      "/webjars/**",
      "/configuration/**"
  };
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //http.csrf().disable();
    //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.csrf(csrf -> csrf.disable());
    http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers(SWAGGER_WHITE_LIST).permitAll()
        .requestMatchers("/auth/**").permitAll()
        .requestMatchers(HttpMethod.GET,"/categories/**").permitAll()
        .requestMatchers(HttpMethod.POST,"/products").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
        .requestMatchers(HttpMethod.PUT,"/products/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST,"/orders").hasAuthority("USER")
        .requestMatchers(HttpMethod.GET,"/orders/**").hasAuthority("USER")
        .requestMatchers(HttpMethod.GET,"/users/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
    );

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

}
