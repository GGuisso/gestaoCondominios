package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.ProfissionalDTO;

import java.util.List;

public interface IProfissionalService {
    ProfissionalDTO saveProfissional(ProfissionalDTO profissionalDTO);
    ProfissionalDTO updateProfissional(Long id, ProfissionalDTO profissionalDTO);
    ProfissionalDTO getProfissionalById(Long id);
    List<ProfissionalDTO> getAllProfissionais();  // Novo método adicionado
    void deleteProfissional(Long id);

    ProfissionalDTO getProfissionalByCpf(String cpf);   // Método para busca por CPF
    List<ProfissionalDTO> getProfissionaisByEmail(String email);   // Método para busca por email
    List<ProfissionalDTO> getProfissionaisByStatus(String status); // Método para busca por status
}
