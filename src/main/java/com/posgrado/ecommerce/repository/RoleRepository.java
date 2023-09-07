package com.posgrado.ecommerce.repository;

import com.posgrado.ecommerce.entity.Role;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

  Optional<Role> findByName(String name);

  @Query(value = "SELECT name FROM roles WHERE roles.name = ?1",nativeQuery = true)
  String findStringByName(String name);


}

