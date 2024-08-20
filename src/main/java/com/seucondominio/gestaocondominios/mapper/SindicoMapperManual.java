package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.SindicoDTO;
import com.seucondominio.gestaocondominios.entities.*;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SindicoMapperManual {

    private final CondominioRepository condominioRepository;
    private final UsuarioRepository usuarioRepository;

    public SindicoMapperManual(CondominioRepository condominioRepository, UsuarioRepository usuarioRepository) {
        this.condominioRepository = condominioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Sindico toEntity(SindicoDTO sindicoDTO) {
        Sindico sindico = new Sindico();
        sindico.setId(sindicoDTO.getId());
        sindico.setNome(sindicoDTO.getNome());
        sindico.setSobrenome(sindicoDTO.getSobrenome());
        sindico.setCpf(sindicoDTO.getCpf());
        sindico.setTelefone(sindicoDTO.getTelefone());
        sindico.setEmail(sindicoDTO.getEmail());
        sindico.setProfissional(sindicoDTO.isProfissional());

        if (sindicoDTO.getUsuarioId() != null) {
            sindico.setUsuario(usuarioRepository.findById(sindicoDTO.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + sindicoDTO.getUsuarioId())));
        }

        if (sindicoDTO.getCondominioIds() != null) {
            sindico.setCondominios(sindicoDTO.getCondominioIds().stream()
                .map(id -> condominioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + id)))
                .collect(Collectors.toSet()));
        }

        return sindico;
    }

    public SindicoDTO toDTO(Sindico sindico) {
        SindicoDTO sindicoDTO = new SindicoDTO();
        sindicoDTO.setId(sindico.getId());
        sindicoDTO.setNome(sindico.getNome());
        sindicoDTO.setSobrenome(sindico.getSobrenome());
        sindicoDTO.setCpf(sindico.getCpf());
        sindicoDTO.setTelefone(sindico.getTelefone());
        sindicoDTO.setEmail(sindico.getEmail());
        sindicoDTO.setProfissional(sindico.isProfissional());

        if (sindico.getUsuario() != null) {
            sindicoDTO.setUsuarioId(sindico.getUsuario().getId());
        }

        if (sindico.getCondominios() != null) {
            sindicoDTO.setCondominioIds(sindico.getCondominios().stream()
                .map(Condominio::getId)
                .collect(Collectors.toSet()));
        }

        return sindicoDTO;
    }
}
