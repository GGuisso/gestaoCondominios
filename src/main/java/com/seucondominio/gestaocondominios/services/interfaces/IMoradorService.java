package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import java.util.List;

public interface IMoradorService {
    MoradorDTO saveMorador(MoradorDTO moradorDTO);
    MoradorDTO updateMorador(Long id, MoradorDTO moradorDTO);
    MoradorDTO getMoradorById(Long id);
    List<MoradorDTO> getAllMoradores();
    void deleteMorador(Long id);

    MoradorDTO getMoradorByCpf(String cpf);   // Novo método para busca por CPF
    List<MoradorDTO> getMoradoresByEmail(String email);   // Novo método para busca por email
}
