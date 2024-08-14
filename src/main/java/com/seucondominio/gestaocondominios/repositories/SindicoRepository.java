package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.Sindico;

@Repository
public interface SindicoRepository extends JpaRepository<Sindico, Long> {
}

