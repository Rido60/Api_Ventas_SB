package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.RoleDto;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.exception.RoleAlreadyTaken;
import com.posgrado.ecommerce.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

  RoleRepository roleRepository;

  @Override
  public Role getByName(String name) {
    return roleRepository.findByName(name)
        .orElseThrow(() -> new EntityNotFoundException("Role with name: " + name +", not Found "));
  }

  @Override
  public Role saveRole(Role role) {
    Role rol = new Role();
    String roleExist = roleRepository.findStringByName(role.getName());
    if (roleExist == null){
      Role roleSaved = roleRepository.save(role);
      rol = roleSaved;
    }else{
      rol = role;
      throw new RoleAlreadyTaken(role.getName());
    }
    return rol;
  }




}
