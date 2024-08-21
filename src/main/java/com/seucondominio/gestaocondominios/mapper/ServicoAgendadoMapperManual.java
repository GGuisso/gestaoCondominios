package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;
import com.seucondominio.gestaocondominios.dto.ServicoAgendadoDTO;
import com.seucondominio.gestaocondominios.entities.Anexo;
import com.seucondominio.gestaocondominios.entities.ServicoAgendado;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicoAgendadoMapperManual {

    private final ProfissionalRepository profissionalRepository;
    private final CondominioRepository condominioRepository;
    private final MoradorRepository moradorRepository;
    private final AnexoMapperManual anexoMapperManual;

    public ServicoAgendadoMapperManual(ProfissionalRepository profissionalRepository, 
                                       CondominioRepository condominioRepository,
                                       MoradorRepository moradorRepository,
                                       AnexoMapperManual anexoMapperManual) {
        this.profissionalRepository = profissionalRepository;
        this.condominioRepository = condominioRepository;
        this.moradorRepository = moradorRepository;
        this.anexoMapperManual = anexoMapperManual;
    }

    public ServicoAgendado toEntity(ServicoAgendadoDTO dto) {
        ServicoAgendado servicoAgendado = new ServicoAgendado();
        servicoAgendado.setId(dto.getId());
        servicoAgendado.setDataHora(dto.getDataHora());
        servicoAgendado.setDescricao(dto.getDescricao());
        
        if (dto.getProfissionalId() != null) {
            servicoAgendado.setProfissional(
                profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"))
            );
        }

        if (dto.getCondominioId() != null) {
            servicoAgendado.setCondominio(
                condominioRepository.findById(dto.getCondominioId())
                .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado"))
            );
        }

        if (dto.getMoradorId() != null) {
            servicoAgendado.setMorador(
                moradorRepository.findById(dto.getMoradorId())
                .orElse(null)
            );
        }

        List<Anexo> anexos = dto.getAnexos().stream()
            .map(anexoDTO -> anexoMapperManual.toEntity(anexoDTO))
            .collect(Collectors.toList());
        servicoAgendado.setAnexos(anexos);

        return servicoAgendado;
    }

    public ServicoAgendadoDTO toDTO(ServicoAgendado entity) {
        ServicoAgendadoDTO dto = new ServicoAgendadoDTO();
        dto.setId(entity.getId());
        dto.setDataHora(entity.getDataHora());
        dto.setDescricao(entity.getDescricao());
        dto.setProfissionalId(entity.getProfissional().getId());
        dto.setCondominioId(entity.getCondominio().getId());

        if (entity.getMorador() != null) {
            dto.setMoradorId(entity.getMorador().getId());
        }

        List<AnexoDTO> anexoDTOs = entity.getAnexos().stream()
            .map(anexo -> anexoMapperManual.toDTO(anexo))
            .collect(Collectors.toList());
        dto.setAnexos(anexoDTOs);

        return dto;
    }
}
