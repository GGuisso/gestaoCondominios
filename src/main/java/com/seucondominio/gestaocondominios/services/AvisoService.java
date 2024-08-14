package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.AvisoDTO;
import com.seucondominio.gestaocondominios.entities.Aviso;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.AvisoMapperManual;
import com.seucondominio.gestaocondominios.repositories.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvisoService implements IAvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    @Autowired
    private AvisoMapperManual avisoMapperManual;

    @Override
    public AvisoDTO saveAviso(AvisoDTO avisoDTO) {
        Aviso aviso = avisoMapperManual.toEntity(avisoDTO);
        aviso = avisoRepository.save(aviso);
        return avisoMapperManual.toDTO(aviso);
    }

    @Override
    public AvisoDTO updateAviso(Long id, AvisoDTO avisoDTO) {
        Aviso aviso = findAvisoById(id);
        aviso = avisoMapperManual.toEntity(avisoDTO);
        aviso.setId(id);  // Garantir que o ID do aviso seja mantido
        aviso = avisoRepository.save(aviso);
        return avisoMapperManual.toDTO(aviso);
    }

    @Override
    public AvisoDTO getAvisoById(Long id) {
        Aviso aviso = findAvisoById(id);
        return avisoMapperManual.toDTO(aviso);
    }

    @Override
    public List<AvisoDTO> getAllAvisos() {
        return avisoRepository.findAll().stream()
            .map(avisoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteAviso(Long id) {
        Aviso aviso = findAvisoById(id);
        avisoRepository.delete(aviso);
    }

    // Método privado para centralizar a lógica de busca do Aviso
    private Aviso findAvisoById(Long id) {
        return avisoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Aviso não encontrado com ID: " + id));
    }
}
