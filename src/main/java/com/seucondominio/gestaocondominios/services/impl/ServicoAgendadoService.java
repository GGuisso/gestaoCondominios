package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.ServicoAgendadoDTO;
import com.seucondominio.gestaocondominios.entities.ServicoAgendado;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.ServicoAgendadoMapperManual;
import com.seucondominio.gestaocondominios.repositories.ServicoAgendadoRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IServicoAgendadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicoAgendadoService implements IServicoAgendadoService {

    @Autowired
    private ServicoAgendadoRepository servicoAgendadoRepository;

    @Autowired
    private ServicoAgendadoMapperManual servicoAgendadoMapperManual;

    @Override
    public ServicoAgendadoDTO saveServicoAgendado(ServicoAgendadoDTO servicoAgendadoDTO) {
        ServicoAgendado servicoAgendado = servicoAgendadoMapperManual.toEntity(servicoAgendadoDTO);
        servicoAgendado = servicoAgendadoRepository.save(servicoAgendado);
        return servicoAgendadoMapperManual.toDTO(servicoAgendado);
    }

    @Override
    public ServicoAgendadoDTO updateServicoAgendado(Long id, ServicoAgendadoDTO servicoAgendadoDTO) {
        ServicoAgendado servicoAgendado = findServicoAgendadoById(id);
        servicoAgendadoMapperManual.toEntity(servicoAgendadoDTO);
        servicoAgendado.setId(id);  // Garantir que o ID do servicoAgendado seja mantido
        servicoAgendado = servicoAgendadoRepository.save(servicoAgendado);
        return servicoAgendadoMapperManual.toDTO(servicoAgendado);
    }

    @Override
    public ServicoAgendadoDTO getServicoAgendadoById(Long id) {
        ServicoAgendado servicoAgendado = findServicoAgendadoById(id);
        return servicoAgendadoMapperManual.toDTO(servicoAgendado);
    }

    @Override
    public List<ServicoAgendadoDTO> getAllServicosAgendados() {
        return servicoAgendadoRepository.findAll().stream()
            .map(servicoAgendadoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteServicoAgendado(Long id) {
        ServicoAgendado servicoAgendado = findServicoAgendadoById(id);
        servicoAgendadoRepository.delete(servicoAgendado);
    }

    // Método privado para centralizar a lógica de busca do ServicoAgendado
    private ServicoAgendado findServicoAgendadoById(Long id) {
        return servicoAgendadoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Serviço Agendado não encontrado com ID: " + id));
    }
}
