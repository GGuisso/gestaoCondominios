package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
