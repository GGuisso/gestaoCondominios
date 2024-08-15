package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.ChamadoDTO;
import com.seucondominio.gestaocondominios.entities.Chamado;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.ChamadoMapperManual;
import com.seucondominio.gestaocondominios.repositories.ChamadoRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IChamadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChamadoService implements IChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ChamadoMapperManual chamadoMapperManual;

    @Override
    public ChamadoDTO saveChamado(ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoMapperManual.toEntity(chamadoDTO);
        chamado = chamadoRepository.save(chamado);
        return chamadoMapperManual.toDTO(chamado);
    }

    @Override
    public ChamadoDTO updateChamado(Long id, ChamadoDTO chamadoDTO) {
        Chamado chamado = findChamadoById(id);
        chamado = chamadoMapperManual.toEntity(chamadoDTO);
        chamado.setId(id);  // Garantir que o ID do chamado seja mantido
        chamado = chamadoRepository.save(chamado);
        return chamadoMapperManual.toDTO(chamado);
    }

    @Override
    public ChamadoDTO getChamadoById(Long id) {
        Chamado chamado = findChamadoById(id);
        return chamadoMapperManual.toDTO(chamado);
    }

    @Override
    public List<ChamadoDTO> getAllChamados() {
        return chamadoRepository.findAll().stream()
            .map(chamadoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteChamado(Long id) {
        Chamado chamado = findChamadoById(id);
        chamadoRepository.delete(chamado);
    }

    @Override
    public List<ChamadoDTO> getChamadosByMoradorId(Long moradorId) {
        return chamadoRepository.findByMoradorId(moradorId).stream()
            .map(chamadoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<ChamadoDTO> getChamadosBySindicoId(Long sindicoId) {
        return chamadoRepository.findBySindicoAtendenteId(sindicoId).stream()
            .map(chamadoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<ChamadoDTO> getChamadosByStatus(String status) {
        return chamadoRepository.findByStatus(status).stream()
            .map(chamadoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    // Método privado para centralizar a lógica de busca do Chamado
    private Chamado findChamadoById(Long id) {
        return chamadoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado com ID: " + id));
    }
}
