package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.ProfissionalDTO;
import com.seucondominio.gestaocondominios.entities.Profissional;
import com.seucondominio.gestaocondominios.repositories.AnexoRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfissionalMapperManual {

    private final AnexoRepository anexoRepository;

    public ProfissionalMapperManual(AnexoRepository anexoRepository) {
        this.anexoRepository = anexoRepository;
    }

    public Profissional toEntity(ProfissionalDTO profissionalDTO) {
        Profissional profissional = new Profissional();
        profissional.setId(profissionalDTO.getId());
        profissional.setNome(profissionalDTO.getNome());
        profissional.setSobrenome(profissionalDTO.getSobrenome());
        profissional.setCpf(profissionalDTO.getCpf());
        profissional.setTelefone(profissionalDTO.getTelefone());
        profissional.setEmail(profissionalDTO.getEmail());
        profissional.setDataCadastro(profissionalDTO.getDataCadastro());
        profissional.setStatus(profissionalDTO.getStatus());
        profissional.setDataInativacao(profissionalDTO.getDataInativacao());

        if (profissionalDTO.getDocumentosIds() != null) {
            profissional.setDocumentos(profissionalDTO.getDocumentosIds().stream()
                .map(id -> anexoRepository.findById(id).orElse(null))
                .collect(Collectors.toList()));
        }

        return profissional;
    }

    public ProfissionalDTO toDTO(Profissional profissional) {
        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setId(profissional.getId());
        profissionalDTO.setNome(profissional.getNome());
        profissionalDTO.setSobrenome(profissional.getSobrenome());
        profissionalDTO.setCpf(profissional.getCpf());
        profissionalDTO.setTelefone(profissional.getTelefone());
        profissionalDTO.setEmail(profissional.getEmail());
        profissionalDTO.setDataCadastro(profissional.getDataCadastro());
        profissionalDTO.setStatus(profissional.getStatus());
        profissionalDTO.setDataInativacao(profissional.getDataInativacao());

        if (profissional.getDocumentos() != null) {
            profissionalDTO.setDocumentosIds(profissional.getDocumentos().stream()
                .map(anexo -> anexo.getId())
                .collect(Collectors.toList()));
        }

        return profissionalDTO;
    }
}
