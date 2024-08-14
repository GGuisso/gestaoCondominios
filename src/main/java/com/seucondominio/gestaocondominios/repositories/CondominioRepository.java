package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.Condominio;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {
}

