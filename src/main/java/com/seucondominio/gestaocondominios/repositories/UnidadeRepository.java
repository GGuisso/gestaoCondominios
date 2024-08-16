package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}
