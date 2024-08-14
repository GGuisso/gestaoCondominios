package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.AreaComumDTO;
import com.seucondominio.gestaocondominios.entities.AreaComum;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.AreaComumMapperManual;
import com.seucondominio.gestaocondominios.repositories.AreaComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AreaComumService implements IAreaComumService {

    @Autowired
    private AreaComumRepository areaComumRepository;

    @Autowired
    private AreaComumMapperManual areaComumMapperManual;

    @Override
    public AreaComumDTO saveAreaComum(AreaComumDTO areaComumDTO) {
        AreaComum areaComum = areaComumMapperManual.toEntity(areaComumDTO);
        areaComum = areaComumRepository.save(areaComum);
        return areaComumMapperManual.toDTO(areaComum);
    }

    @Override
    public AreaComumDTO updateAreaComum(Long id, AreaComumDTO areaComumDTO) {
        AreaComum areaComum = findAreaComumById(id);
        areaComum = areaComumMapperManual.toEntity(areaComumDTO);
        areaComum.setId(id);  // Garantir que o ID da área comum seja mantido
        areaComum = areaComumRepository.save(areaComum);
        return areaComumMapperManual.toDTO(areaComum);
    }

    @Override
    public AreaComumDTO getAreaComumById(Long id) {
        AreaComum areaComum = findAreaComumById(id);
        return areaComumMapperManual.toDTO(areaComum);
    }

    @Override
    public List<AreaComumDTO> getAllAreasComuns() {
        return areaComumRepository.findAll().stream()
            .map(areaComumMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteAreaComum(Long id) {
        AreaComum areaComum = findAreaComumById(id);
        areaComumRepository.delete(areaComum);
    }

    // Método privado para centralizar a lógica de busca da Área Comum
    private AreaComum findAreaComumById(Long id) {
        return areaComumRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Área Comum não encontrada com ID: " + id));
    }
}
