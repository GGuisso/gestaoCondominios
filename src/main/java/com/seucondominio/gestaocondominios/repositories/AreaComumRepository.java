package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.AreaComum;

@Repository
public interface AreaComumRepository extends JpaRepository<AreaComum, Long> {
}
