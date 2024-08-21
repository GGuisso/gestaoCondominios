package com.seucondominio.gestaocondominios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seucondominio.gestaocondominios.entities.ServicoAgendado;

@Repository
public interface ServicoAgendadoRepository extends JpaRepository<ServicoAgendado, Long> {

}
