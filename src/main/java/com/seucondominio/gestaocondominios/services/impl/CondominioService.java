package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.CondominioMapperManual;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
import com.seucondominio.gestaocondominios.services.interfaces.ICondominioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CondominioService implements ICondominioService {

    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private CondominioMapperManual condominioMapperManual;

    @Override
    public CondominioDTO saveCondominio(CondominioDTO condominioDTO) {
        Condominio condominio = condominioMapperManual.toEntity(condominioDTO);
        condominio = condominioRepository.save(condominio);
        return condominioMapperManual.toDTO(condominio);
    }

    @Override
    public CondominioDTO updateCondominio(Long id, CondominioDTO condominioDTO) {
        Condominio condominio = findCondominioById(id);
        condominio = condominioMapperManual.toEntity(condominioDTO);
        condominio.setId(id);  // Garantir que o ID do condomínio seja mantido
        condominio = condominioRepository.save(condominio);
        return condominioMapperManual.toDTO(condominio);
    }

    @Override
    public CondominioDTO getCondominioById(Long id) {
        Condominio condominio = findCondominioById(id);
        return condominioMapperManual.toDTO(condominio);
    }

    @Override
    public List<CondominioDTO> getAllCondominios() {
        return condominioRepository.findAll().stream()
            .map(condominioMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteCondominio(Long id) {
        Condominio condominio = findCondominioById(id);
        condominioRepository.delete(condominio);
    }

    // Método privado para centralizar a lógica de busca do Condominio
    private Condominio findCondominioById(Long id) {
        return condominioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + id));
    }
}
