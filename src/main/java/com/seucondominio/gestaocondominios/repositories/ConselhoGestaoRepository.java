package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.ConselhoGestao;

@Repository
public interface ConselhoGestaoRepository extends JpaRepository<ConselhoGestao, Long> {
}
