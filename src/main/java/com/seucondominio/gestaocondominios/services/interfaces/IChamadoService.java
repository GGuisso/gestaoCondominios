package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.ChamadoDTO;
import java.util.List;

public interface IChamadoService {
    ChamadoDTO saveChamado(ChamadoDTO chamadoDTO);
    ChamadoDTO updateChamado(Long id, ChamadoDTO chamadoDTO);
    ChamadoDTO getChamadoById(Long id);
    List<ChamadoDTO> getAllChamados();
    void deleteChamado(Long id);

    List<ChamadoDTO> getChamadosByMoradorId(Long moradorId);
    List<ChamadoDTO> getChamadosBySindicoId(Long sindicoId);
    List<ChamadoDTO> getChamadosByStatus(String status);
}
