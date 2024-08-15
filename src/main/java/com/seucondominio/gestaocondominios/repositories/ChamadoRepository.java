package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.Chamado;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByMoradorId(Long moradorId);
    List<Chamado> findBySindicoAtendenteId(Long sindicoId);
    List<Chamado> findByStatus(String status);
}
