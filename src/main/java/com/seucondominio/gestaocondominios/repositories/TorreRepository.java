package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.Torre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorreRepository extends JpaRepository<Torre, Long> {
}
