package com.posgrado.ecommerce.service;


import com.posgrado.ecommerce.dto.UserDto;
import com.posgrado.ecommerce.entity.User;
import java.util.UUID;


public interface UserService {

  UserDto getById(UUID id);

  boolean existByEmail(String email);

  User save(User user);

  void enableUser(User user);

  User findById(UUID id);

}
