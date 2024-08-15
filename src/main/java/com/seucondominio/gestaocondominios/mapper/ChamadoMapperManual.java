package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;
import com.seucondominio.gestaocondominios.dto.ChamadoDTO;
import com.seucondominio.gestaocondominios.entities.AnexoChamado;
import com.seucondominio.gestaocondominios.entities.Chamado;
import com.seucondominio.gestaocondominios.entities.Morador;
import com.seucondominio.gestaocondominios.entities.Sindico;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.MoradorRepository;
import com.seucondominio.gestaocondominios.repositories.SindicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChamadoMapperManual {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private SindicoRepository sindicoRepository;

    @Autowired
    private AnexoMapperManual anexoMapperManual;

    public Chamado toEntity(ChamadoDTO chamadoDTO) {
        Chamado chamado = new Chamado();
        chamado.setId(chamadoDTO.getId());
        chamado.setDescricao(chamadoDTO.getDescricao());
        chamado.setTipo(chamadoDTO.getTipo());
        chamado.setStatus(chamadoDTO.getStatus());
        chamado.setDataAbertura(chamadoDTO.getDataAbertura());
        chamado.setDataFechamento(chamadoDTO.getDataFechamento());
        chamado.setDescricaoAtendimento(chamadoDTO.getDescricaoAtendimento());
        chamado.setDescricaoSolucao(chamadoDTO.getDescricaoSolucao());

        // Mapeando o Morador
        Morador morador = moradorRepository.findById(chamadoDTO.getMoradorId())
            .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + chamadoDTO.getMoradorId()));
        chamado.setMorador(morador);

        // Mapeando o Sindico, se presente
        if (chamadoDTO.getSindicoId() != null) {
            Sindico sindico = sindicoRepository.findById(chamadoDTO.getSindicoId())
                .orElseThrow(() -> new EntityNotFoundException("Sindico não encontrado com ID: " + chamadoDTO.getSindicoId()));
            chamado.setSindicoAtendente(sindico);
        }

        // Mapeando os anexos
        List<AnexoChamado> anexos = chamadoDTO.getAnexos().stream()
            .map(anexoMapperManual::toEntity)
            .collect(Collectors.toList());
        chamado.setAnexos(anexos);

        return chamado;
    }

    public ChamadoDTO toDTO(Chamado chamado) {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setId(chamado.getId());
        chamadoDTO.setDescricao(chamado.getDescricao());
        chamadoDTO.setTipo(chamado.getTipo());
        chamadoDTO.setStatus(chamado.getStatus());
        chamadoDTO.setDataAbertura(chamado.getDataAbertura());
        chamadoDTO.setDataFechamento(chamado.getDataFechamento());
        chamadoDTO.setDescricaoAtendimento(chamado.getDescricaoAtendimento());
        chamadoDTO.setDescricaoSolucao(chamado.getDescricaoSolucao());
        chamadoDTO.setMoradorId(chamado.getMorador().getId());
        
        if (chamado.getSindicoAtendente() != null) {
            chamadoDTO.setSindicoId(chamado.getSindicoAtendente().getId());
        }

        // Mapeando os anexos
        List<AnexoDTO> anexos = chamado.getAnexos().stream()
            .map(anexoMapperManual::toDTO)
            .collect(Collectors.toList());
        chamadoDTO.setAnexos(anexos);

        return chamadoDTO;
    }
}
