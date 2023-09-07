package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.EmailNotification;
import com.posgrado.ecommerce.dto.UserDto;
import com.posgrado.ecommerce.entity.ConfirmationToken;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.entity.User;
import com.posgrado.ecommerce.exception.EmailAlreadyTaken;
import com.posgrado.ecommerce.util.TemplateGenerator;
import com.posgrado.ecommerce.util.UrlGenerator;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

  private UserService userService;
  private ConfimationTokenService confimationTokenService;
  private EmailService emailService;
  private PasswordEncoder passwordEncoder;
  private RoleService roleService;

  public String register(UserDto userDto){
    boolean existsEmail = userService.existByEmail(userDto.getEmail());
    if(existsEmail){
        throw new EmailAlreadyTaken(userDto.getEmail());
      }
    // TODO: ENCRIPTAR PASSWORD

    User user= new User();
    user.setFirstName(userDto.getFirstname());
    user.setLastName(userDto.getLastname());
    user.setEmail(userDto.getEmail());
    String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
    user.setPassword(encryptedPassword);
    Role defaultRole = roleService.getByName("USER");
    user.setRole(defaultRole);
    user.setAddress(userDto.getAddress());
    User userSaved = userService.save(user);

    String token = UUID.randomUUID().toString();

    confimationTokenService.save(
        new ConfirmationToken(
        token,
        LocalDateTime.now().plusMinutes(15),
        userSaved)
    );


    String url = UrlGenerator.create("/auth/confirm","token", token);
    String template = TemplateGenerator.generateTemplateConfirmationToken(userSaved.getFirstName(),url );

    EmailNotification email = EmailNotification.builder()
        .to(userSaved.getEmail())
        .subject("Account Confirmation")
        .body(template)
        .hasTemplate(true)
        .build();

    emailService.send(email);

    return "User Register successfully whit id: "+ userSaved.getId();
  }


  public String confirm(String token){
    ConfirmationToken confirmationToken = confimationTokenService.getByToken(token);
    if(confirmationToken.getConfirmedAt() != null){
      throw new RuntimeException("Token is already confirmed");
    }
    if(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())){
      throw new RuntimeException("Token expired");
    }
    userService.enableUser(confirmationToken.getUser());
    confimationTokenService.setConfirmedAt(confirmationToken);
    return "Account confirmed successfully";

  }


}
