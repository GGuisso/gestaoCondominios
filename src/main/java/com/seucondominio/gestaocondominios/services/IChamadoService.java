package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.ChamadoDTO;
import java.util.List;

public interface IChamadoService {
    ChamadoDTO saveChamado(ChamadoDTO chamadoDTO);
    ChamadoDTO updateChamado(Long id, ChamadoDTO chamadoDTO);
    ChamadoDTO getChamadoById(Long id);
    List<ChamadoDTO> getAllChamados();
    void deleteChamado(Long id);
}
