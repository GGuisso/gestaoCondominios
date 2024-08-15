package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;
import com.seucondominio.gestaocondominios.entities.AnexoChamado;
import com.seucondominio.gestaocondominios.mapper.AnexoMapperManual;
import com.seucondominio.gestaocondominios.repositories.AnexoRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IAnexoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnexoService implements IAnexoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private AnexoMapperManual anexoMapperManual;

    @Override
    public AnexoDTO saveAnexo(AnexoDTO anexoDTO) {
        AnexoChamado anexo = anexoMapperManual.toEntity(anexoDTO);
        anexo = anexoRepository.save(anexo);
        return anexoMapperManual.toDTO(anexo);
    }

    @Override
    public List<AnexoDTO> getAnexosByChamadoId(Long chamadoId) {
        return anexoRepository.findAll().stream()
            .filter(anexo -> anexo.getChamado().getId().equals(chamadoId))
            .map(anexoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteAnexo(Long id) {
        anexoRepository.deleteById(id);
    }
}