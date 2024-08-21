package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;
import com.seucondominio.gestaocondominios.entities.Anexo;
import com.seucondominio.gestaocondominios.repositories.ChamadoRepository;
import com.seucondominio.gestaocondominios.repositories.ServicoAgendadoRepository;
import org.springframework.stereotype.Component;

@Component
public class AnexoMapperManual {

    private final ChamadoRepository chamadoRepository;
    private final ServicoAgendadoRepository servicoAgendadoRepository;

    public AnexoMapperManual(ChamadoRepository chamadoRepository, ServicoAgendadoRepository servicoAgendadoRepository) {
        this.chamadoRepository = chamadoRepository;
        this.servicoAgendadoRepository = servicoAgendadoRepository;
    }

    public Anexo toEntity(AnexoDTO anexoDTO) {
        Anexo anexo = new Anexo();
        anexo.setId(anexoDTO.getId());
        anexo.setNome(anexoDTO.getNome());
        anexo.setCaminhoArquivo(anexoDTO.getCaminhoArquivo());
        anexo.setDescricao(anexoDTO.getDescricao());

        if (anexoDTO.getChamadoId() != null) {
            anexo.setChamado(chamadoRepository.findById(anexoDTO.getChamadoId())
                .orElse(null));
        }

        if (anexoDTO.getServicoAgendadoId() != null) {
            anexo.setServicoAgendado(servicoAgendadoRepository.findById(anexoDTO.getServicoAgendadoId())
                .orElse(null));
        }

        return anexo;
    }

    public AnexoDTO toDTO(Anexo anexo) {
        AnexoDTO anexoDTO = new AnexoDTO();
        anexoDTO.setId(anexo.getId());
        anexoDTO.setNome(anexo.getNome());
        anexoDTO.setCaminhoArquivo(anexo.getCaminhoArquivo());
        anexoDTO.setDescricao(anexo.getDescricao());

        if (anexo.getChamado() != null) {
            anexoDTO.setChamadoId(anexo.getChamado().getId());
        }

        if (anexo.getServicoAgendado() != null) {
            anexoDTO.setServicoAgendadoId(anexo.getServicoAgendado().getId());
        }

        return anexoDTO;
    }
}
