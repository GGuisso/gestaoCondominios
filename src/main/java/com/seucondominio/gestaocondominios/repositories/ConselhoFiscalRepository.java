package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.ConselhoFiscal;

@Repository
public interface ConselhoFiscalRepository extends JpaRepository<ConselhoFiscal, Long> {
}
