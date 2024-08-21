package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.ServicoAgendadoDTO;
import java.util.List;

public interface IServicoAgendadoService {
    ServicoAgendadoDTO saveServicoAgendado(ServicoAgendadoDTO servicoAgendadoDTO);
    ServicoAgendadoDTO updateServicoAgendado(Long id, ServicoAgendadoDTO servicoAgendadoDTO);
    ServicoAgendadoDTO getServicoAgendadoById(Long id);
    List<ServicoAgendadoDTO> getAllServicosAgendados();
    void deleteServicoAgendado(Long id);
}
