package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import com.seucondominio.gestaocondominios.entities.Morador;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.MoradorMapperManual;
import com.seucondominio.gestaocondominios.repositories.MoradorRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IMoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoradorService implements IMoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private MoradorMapperManual moradorMapperManual;

    @Override
    public MoradorDTO saveMorador(MoradorDTO moradorDTO) {
        Morador morador = moradorMapperManual.toEntity(moradorDTO);
        morador = moradorRepository.save(morador);
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public MoradorDTO updateMorador(Long id, MoradorDTO moradorDTO) {
        Morador morador = findMoradorById(id);
        moradorMapperManual.toEntity(moradorDTO);
        morador.setId(id);  // Garantir que o ID do morador seja mantido
        morador = moradorRepository.save(morador);
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public MoradorDTO getMoradorById(Long id) {
        Morador morador = findMoradorById(id);
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public List<MoradorDTO> getAllMoradores() {
        return moradorRepository.findAll().stream()
            .map(moradorMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteMorador(Long id) {
        Morador morador = findMoradorById(id);
        moradorRepository.delete(morador);
    }

    @Override
    public MoradorDTO getMoradorByCpf(String cpf) {
        Morador morador = moradorRepository.findByCpf(cpf)
            .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com CPF: " + cpf));
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public List<MoradorDTO> getMoradoresByEmail(String email) {
        return moradorRepository.findByEmail(email).stream()
            .map(moradorMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    // Método privado para centralizar a lógica de busca do Morador
    private Morador findMoradorById(Long id) {
        return moradorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + id));
    }
}
