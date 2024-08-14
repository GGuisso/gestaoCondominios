package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.AgendamentoDTO;
import com.seucondominio.gestaocondominios.entities.Agendamento;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.AgendamentoMapperManual;
import com.seucondominio.gestaocondominios.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgendamentoService implements IAgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoMapperManual agendamentoMapperManual;

    @Override
    public AgendamentoDTO saveAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoMapperManual.toEntity(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapperManual.toDTO(agendamento);
    }

    @Override
    public AgendamentoDTO updateAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = findAgendamentoById(id);
        agendamento = agendamentoMapperManual.toEntity(agendamentoDTO);
        agendamento.setId(id);  // Garantir que o ID do agendamento seja mantido
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapperManual.toDTO(agendamento);
    }

    @Override
    public AgendamentoDTO getAgendamentoById(Long id) {
        Agendamento agendamento = findAgendamentoById(id);
        return agendamentoMapperManual.toDTO(agendamento);
    }

    @Override
    public List<AgendamentoDTO> getAllAgendamentos() {
        return agendamentoRepository.findAll().stream()
            .map(agendamentoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteAgendamento(Long id) {
        Agendamento agendamento = findAgendamentoById(id);
        agendamentoRepository.delete(agendamento);
    }

    // Método privado para centralizar a lógica de busca do Agendamento
    private Agendamento findAgendamentoById(Long id) {
        return agendamentoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + id));
    }
}
