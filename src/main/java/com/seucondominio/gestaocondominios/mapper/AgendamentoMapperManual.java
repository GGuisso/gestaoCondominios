package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.AgendamentoDTO;
import com.seucondominio.gestaocondominios.entities.Agendamento;
import com.seucondominio.gestaocondominios.entities.AreaComum;
import com.seucondominio.gestaocondominios.entities.Morador;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.AreaComumRepository;
import com.seucondominio.gestaocondominios.repositories.MoradorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapperManual {

    @Autowired
    private AreaComumRepository areaComumRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    public Agendamento toEntity(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(agendamentoDTO.getId());
        agendamento.setDataHoraInicio(agendamentoDTO.getDataHoraInicio());
        agendamento.setDataHoraFim(agendamentoDTO.getDataHoraFim());

        // Mapeando AreaComum e Morador
        AreaComum areaComum = areaComumRepository.findById(agendamentoDTO.getAreaComumId())
            .orElseThrow(() -> new EntityNotFoundException("Área Comum não encontrada com ID: " + agendamentoDTO.getAreaComumId()));
        agendamento.setAreaComum(areaComum);

        Morador morador = moradorRepository.findById(agendamentoDTO.getMoradorId())
            .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + agendamentoDTO.getMoradorId()));
        agendamento.setMorador(morador);

        return agendamento;
    }

    public AgendamentoDTO toDTO(Agendamento agendamento) {
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
        agendamentoDTO.setId(agendamento.getId());
        agendamentoDTO.setDataHoraInicio(agendamento.getDataHoraInicio());
        agendamentoDTO.setDataHoraFim(agendamento.getDataHoraFim());
        agendamentoDTO.setAreaComumId(agendamento.getAreaComum().getId());
        agendamentoDTO.setMoradorId(agendamento.getMorador().getId());
        return agendamentoDTO;
    }
}
