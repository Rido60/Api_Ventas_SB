package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.RoleDto;
import com.posgrado.ecommerce.entity.Role;

public interface RoleService {

  Role getByName(String name);

  Role saveRole(Role role);
}
