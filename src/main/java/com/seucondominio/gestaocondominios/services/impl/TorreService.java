package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.TorreDTO;
import com.seucondominio.gestaocondominios.entities.Torre;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.TorreMapperManual;
import com.seucondominio.gestaocondominios.repositories.TorreRepository;
import com.seucondominio.gestaocondominios.services.interfaces.ITorreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TorreService implements ITorreService {

    @Autowired
    private TorreRepository torreRepository;

    @Autowired
    private TorreMapperManual torreMapperManual;

    @Override
    public TorreDTO saveTorre(TorreDTO torreDTO) {
        Torre torre = torreMapperManual.toEntity(torreDTO);
        torre = torreRepository.save(torre);
        return torreMapperManual.toDTO(torre);
    }

    @Override
    public TorreDTO updateTorre(Long id, TorreDTO torreDTO) {
        Torre torre = findTorreById(id);
        torre = torreMapperManual.toEntity(torreDTO);
        torre.setId(id);
        torre = torreRepository.save(torre);
        return torreMapperManual.toDTO(torre);
    }

    @Override
    public TorreDTO getTorreById(Long id) {
        Torre torre = findTorreById(id);
        return torreMapperManual.toDTO(torre);
    }

    @Override
    public List<TorreDTO> getAllTorres() {
        return torreRepository.findAll().stream()
            .map(torreMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteTorre(Long id) {
        Torre torre = findTorreById(id);
        torreRepository.delete(torre);
    }

    private Torre findTorreById(Long id) {
        return torreRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Torre não encontrada com ID: " + id));
    }
}
