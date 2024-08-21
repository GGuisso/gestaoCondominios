package com.seucondominio.gestaocondominios.repositories;

import com.seucondominio.gestaocondominios.entities.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {
    List<Anexo> findByChamadoId(Long chamadoId);
    List<Anexo> findByServicoAgendadoId(Long servicoAgendadoId);
    // Outros métodos de busca conforme necessário
}
