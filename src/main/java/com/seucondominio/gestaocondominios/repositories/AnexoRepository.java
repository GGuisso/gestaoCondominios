package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.AnexoChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<AnexoChamado, Long> {
}
