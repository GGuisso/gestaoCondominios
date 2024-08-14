package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.ChamadoDTO;
import com.seucondominio.gestaocondominios.entities.Chamado;
import com.seucondominio.gestaocondominios.entities.Morador;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChamadoMapperManual {

    @Autowired
    private MoradorRepository moradorRepository;

    public Chamado toEntity(ChamadoDTO chamadoDTO) {
        Chamado chamado = new Chamado();
        chamado.setId(chamadoDTO.getId());
        chamado.setDescricao(chamadoDTO.getDescricao());
        chamado.setTipo(chamadoDTO.getTipo());
        chamado.setStatus(chamadoDTO.getStatus());
        chamado.setDataAbertura(chamadoDTO.getDataAbertura());
        chamado.setDataFechamento(chamadoDTO.getDataFechamento());

        // Mapeando o Morador
        Morador morador = moradorRepository.findById(chamadoDTO.getMoradorId())
            .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + chamadoDTO.getMoradorId()));
        chamado.setMorador(morador);

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
        chamadoDTO.setMoradorId(chamado.getMorador().getId());
        return chamadoDTO;
    }
}
