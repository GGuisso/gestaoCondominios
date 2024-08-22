package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.SindicoDTO;
import com.seucondominio.gestaocondominios.entities.Sindico;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.SindicoMapperManual;
import com.seucondominio.gestaocondominios.repositories.SindicoRepository;
import com.seucondominio.gestaocondominios.services.interfaces.ISindicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SindicoService implements ISindicoService {

    @Autowired
    private SindicoRepository sindicoRepository;

    @Autowired
    private SindicoMapperManual sindicoMapperManual;

    @Override
    public SindicoDTO saveSindico(SindicoDTO sindicoDTO) {
        Sindico sindico = sindicoMapperManual.toEntity(sindicoDTO);
        sindico = sindicoRepository.save(sindico);
        return sindicoMapperManual.toDTO(sindico);
    }

    @Override
    public SindicoDTO updateSindico(Long id, SindicoDTO sindicoDTO) {
        Sindico sindico = findSindicoById(id);
        sindicoMapperManual.toEntity(sindicoDTO);
        sindico.setId(id);  // Garantir que o ID do síndico seja mantido
        sindico = sindicoRepository.save(sindico);
        return sindicoMapperManual.toDTO(sindico);
    }

    @Override
    public SindicoDTO getSindicoById(Long id) {
        Sindico sindico = findSindicoById(id);
        return sindicoMapperManual.toDTO(sindico);
    }
    
    @Override
    public SindicoDTO getSindicoByCpf(String cpf) {
        return sindicoRepository.findByCpf(cpf)
                .map(sindicoMapperManual::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Síndico não encontrado com CPF: " + cpf));
    }

    @Override
    public SindicoDTO getSindicoByEmail(String email) {
        return sindicoRepository.findByEmail(email)
                .map(sindicoMapperManual::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Síndico não encontrado com e-mail: " + email));
    }

    @Override
    public List<SindicoDTO> getAllSindicos() {
        return sindicoRepository.findAll().stream()
            .map(sindicoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteSindico(Long id) {
        Sindico sindico = findSindicoById(id);
        sindicoRepository.delete(sindico);
    }

    // Método privado para centralizar a lógica de busca do Síndico
    private Sindico findSindicoById(Long id) {
        return sindicoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Síndico não encontrado com ID: " + id));
    }
}
