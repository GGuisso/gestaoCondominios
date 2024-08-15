package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;
import com.seucondominio.gestaocondominios.entities.AnexoChamado;
import com.seucondominio.gestaocondominios.entities.Chamado;
import com.seucondominio.gestaocondominios.entities.Morador;
import com.seucondominio.gestaocondominios.entities.Sindico;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.ChamadoRepository;
import com.seucondominio.gestaocondominios.repositories.MoradorRepository;
import com.seucondominio.gestaocondominios.repositories.SindicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnexoMapperManual {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private SindicoRepository sindicoRepository;

    public AnexoChamado toEntity(AnexoDTO anexoDTO) {
        AnexoChamado anexo = new AnexoChamado();
        anexo.setId(anexoDTO.getId());
        anexo.setNomeArquivo(anexoDTO.getNomeArquivo());
        anexo.setCaminhoArquivo(anexoDTO.getCaminhoArquivo());
        anexo.setDataUpload(anexoDTO.getDataUpload());

        // Mapeando o Chamado
        Chamado chamado = chamadoRepository.findById(anexoDTO.getChamadoId())
            .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado com ID: " + anexoDTO.getChamadoId()));
        anexo.setChamado(chamado);

        // Mapeando o Morador, se presente
        if (anexoDTO.getMoradorId() != null) {
            Morador morador = moradorRepository.findById(anexoDTO.getMoradorId())
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + anexoDTO.getMoradorId()));
            anexo.setMorador(morador);
        }

        // Mapeando o Sindico, se presente
        if (anexoDTO.getSindicoId() != null) {
            Sindico sindico = sindicoRepository.findById(anexoDTO.getSindicoId())
                .orElseThrow(() -> new EntityNotFoundException("Sindico não encontrado com ID: " + anexoDTO.getSindicoId()));
            anexo.setSindico(sindico);
        }

        return anexo;
    }

    public AnexoDTO toDTO(AnexoChamado anexo) {
        AnexoDTO anexoDTO = new AnexoDTO();
        anexoDTO.setId(anexo.getId());
        anexoDTO.setNomeArquivo(anexo.getNomeArquivo());
        anexoDTO.setCaminhoArquivo(anexo.getCaminhoArquivo());
        anexoDTO.setDataUpload(anexo.getDataUpload());
        anexoDTO.setChamadoId(anexo.getChamado().getId());

        if (anexo.getMorador() != null) {
            anexoDTO.setMoradorId(anexo.getMorador().getId());
        }

        if (anexo.getSindico() != null) {
            anexoDTO.setSindicoId(anexo.getSindico().getId());
        }

        return anexoDTO;
    }
}
