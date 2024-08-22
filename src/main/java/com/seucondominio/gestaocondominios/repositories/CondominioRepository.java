package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    Optional<Condominio> findByCnpj(String cnpj);
}
