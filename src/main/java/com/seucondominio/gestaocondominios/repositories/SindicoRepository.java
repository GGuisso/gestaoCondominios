package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.Sindico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SindicoRepository extends JpaRepository<Sindico, Long> {
    Optional<Sindico> findByCpf(String cpf);
    Optional<Sindico> findByEmail(String email);
}
