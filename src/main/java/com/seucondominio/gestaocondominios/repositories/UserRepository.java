package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
