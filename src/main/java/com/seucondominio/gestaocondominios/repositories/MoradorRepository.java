package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.Morador;

import java.util.Optional;
import java.util.List;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {
    Optional<Morador> findByCpf(String cpf);   // Novo método para busca por CPF
    List<Morador> findByEmail(String email);   // Novo método para busca por email
}
