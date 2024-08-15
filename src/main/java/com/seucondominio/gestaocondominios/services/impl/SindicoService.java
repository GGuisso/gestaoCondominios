package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.SindicoDTO;
import com.seucondominio.gestaocondominios.entities.Sindico;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.SindicoMapperManual;
import com.seucondominio.gestaocondominios.repositories.SindicoRepository;
import com.seucondominio.gestaocondominios.services.interfaces.ISindicoService;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
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
    private CondominioRepository condominioRepository;

    @Autowired
    private SindicoMapperManual sindicoMapperManual;

    @Override
    public SindicoDTO saveSindico(SindicoDTO sindicoDTO) {
        Sindico sindico = sindicoMapperManual.toEntity(sindicoDTO);
        sindico.setCondominio(findCondominioById(sindicoDTO.getCondominioId()));
        sindico = sindicoRepository.save(sindico);
        return sindicoMapperManual.toDTO(sindico);
    }

    @Override
    public SindicoDTO updateSindico(Long id, SindicoDTO sindicoDTO) {
        Sindico sindico = findSindicoById(id);
        sindicoMapperManual.toEntity(sindicoDTO);
        sindico.setCondominio(findCondominioById(sindicoDTO.getCondominioId()));
        sindico = sindicoRepository.save(sindico);
        return sindicoMapperManual.toDTO(sindico);
    }

    @Override
    public SindicoDTO getSindicoById(Long id) {
        Sindico sindico = findSindicoById(id);
        return sindicoMapperManual.toDTO(sindico);
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

    // Método privado para centralizar a lógica de busca do Condomínio
    private Condominio findCondominioById(Long id) {
        return condominioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + id));
    }
}
