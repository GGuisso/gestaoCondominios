package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.Aviso;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {
}

