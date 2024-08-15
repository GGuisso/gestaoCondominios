package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.AgendamentoDTO;
import java.util.List;

public interface IAgendamentoService {
    AgendamentoDTO saveAgendamento(AgendamentoDTO agendamentoDTO);
    AgendamentoDTO updateAgendamento(Long id, AgendamentoDTO agendamentoDTO);
    AgendamentoDTO getAgendamentoById(Long id);
    List<AgendamentoDTO> getAllAgendamentos();
    void deleteAgendamento(Long id);
}
