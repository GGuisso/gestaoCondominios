package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.ProfissionalDTO;
import com.seucondominio.gestaocondominios.entities.Profissional;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.ProfissionalMapperManual;
import com.seucondominio.gestaocondominios.repositories.ProfissionalRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfissionalService implements IProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ProfissionalMapperManual profissionalMapperManual;

    @Override
    public ProfissionalDTO saveProfissional(ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalMapperManual.toEntity(profissionalDTO);
        profissional = profissionalRepository.save(profissional);
        return profissionalMapperManual.toDTO(profissional);
    }

    @Override
    public ProfissionalDTO updateProfissional(Long id, ProfissionalDTO profissionalDTO) {
        Profissional profissional = findProfissionalById(id);
        profissionalMapperManual.toEntity(profissionalDTO);
        profissional.setId(id);
        profissional = profissionalRepository.save(profissional);
        return profissionalMapperManual.toDTO(profissional);
    }

    @Override
    public ProfissionalDTO getProfissionalById(Long id) {
        Profissional profissional = findProfissionalById(id);
        return profissionalMapperManual.toDTO(profissional);
    }

    @Override
    public List<ProfissionalDTO> getAllProfissionais() {
        return profissionalRepository.findAll().stream()
            .map(profissionalMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteProfissional(Long id) {
        Profissional profissional = findProfissionalById(id);
        profissionalRepository.delete(profissional);
    }

    @Override
    public ProfissionalDTO getProfissionalByCpf(String cpf) {
        Profissional profissional = profissionalRepository.findByCpf(cpf)
            .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado com CPF: " + cpf));
        return profissionalMapperManual.toDTO(profissional);
    }

    @Override
    public List<ProfissionalDTO> getProfissionaisByEmail(String email) {
        return profissionalRepository.findByEmail(email).stream()
            .map(profissionalMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProfissionalDTO> getProfissionaisByStatus(String status) {
        return profissionalRepository.findByStatus(status).stream()
            .map(profissionalMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    private Profissional findProfissionalById(Long id) {
        return profissionalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado com ID: " + id));
    }
}
