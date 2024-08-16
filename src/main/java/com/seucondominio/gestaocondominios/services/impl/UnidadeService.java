package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.UnidadeDTO;
import com.seucondominio.gestaocondominios.entities.Unidade;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.UnidadeMapperManual;
import com.seucondominio.gestaocondominios.repositories.UnidadeRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IUnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UnidadeService implements IUnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private UnidadeMapperManual unidadeMapperManual;

    @Override
    public UnidadeDTO saveUnidade(UnidadeDTO unidadeDTO) {
        Unidade unidade = unidadeMapperManual.toEntity(unidadeDTO);
        unidade = unidadeRepository.save(unidade);
        return unidadeMapperManual.toDTO(unidade);
    }

    @Override
    public UnidadeDTO updateUnidade(Long id, UnidadeDTO unidadeDTO) {
        Unidade unidade = findUnidadeById(id);
        unidade = unidadeMapperManual.toEntity(unidadeDTO);
        unidade.setId(id);
        unidade = unidadeRepository.save(unidade);
        return unidadeMapperManual.toDTO(unidade);
    }

    @Override
    public UnidadeDTO getUnidadeById(Long id) {
        Unidade unidade = findUnidadeById(id);
        return unidadeMapperManual.toDTO(unidade);
    }

    @Override
    public List<UnidadeDTO> getAllUnidades() {
        return unidadeRepository.findAll().stream()
            .map(unidadeMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteUnidade(Long id) {
        Unidade unidade = findUnidadeById(id);
        unidadeRepository.delete(unidade);
    }

    private Unidade findUnidadeById(Long id) {
        return unidadeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + id));
    }
}
