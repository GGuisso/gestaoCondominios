package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByCpf(String cpf);
    List<Profissional> findByEmail(String email);
    List<Profissional> findByStatus(String status);
}
