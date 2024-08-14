package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import java.util.List;

public interface IMoradorService {
    MoradorDTO saveMorador(MoradorDTO moradorDTO);
    MoradorDTO updateMorador(Long id, MoradorDTO moradorDTO);
    MoradorDTO getMoradorById(Long id);
    List<MoradorDTO> getAllMoradores();
    void deleteMorador(Long id);
}
