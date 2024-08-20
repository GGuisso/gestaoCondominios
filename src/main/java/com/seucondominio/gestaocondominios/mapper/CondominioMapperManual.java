package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import com.seucondominio.gestaocondominios.entities.*;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CondominioMapperManual {

    private final TorreMapperManual torreMapperManual;
    private final SindicoRepository sindicoRepository;
    private final ConselhoGestaoRepository conselhoGestaoRepository;
    private final ConselhoFiscalRepository conselhoFiscalRepository;
    private final UsuarioRepository usuarioRepository;

    public CondominioMapperManual(
            TorreMapperManual torreMapperManual,
            SindicoRepository sindicoRepository,
            ConselhoGestaoRepository conselhoGestaoRepository,
            ConselhoFiscalRepository conselhoFiscalRepository,
            UsuarioRepository usuarioRepository) {
        this.torreMapperManual = torreMapperManual;
        this.sindicoRepository = sindicoRepository;
        this.conselhoGestaoRepository = conselhoGestaoRepository;
        this.conselhoFiscalRepository = conselhoFiscalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Condominio toEntity(CondominioDTO condominioDTO) {
        Condominio condominio = new Condominio();
        condominio.setId(condominioDTO.getId());
        condominio.setNome(condominioDTO.getNome());
        condominio.setEndereco(condominioDTO.getEndereco());
        condominio.setCnpj(condominioDTO.getCnpj());

        if (condominioDTO.getSindicoId() != null) {
            condominio.setSindico(sindicoRepository.findById(condominioDTO.getSindicoId())
                .orElseThrow(() -> new EntityNotFoundException("Síndico não encontrado com ID: " + condominioDTO.getSindicoId())));
        }

        if (condominioDTO.getConselhoGestaoId() != null) {
            condominio.setConselhoGestao(conselhoGestaoRepository.findById(condominioDTO.getConselhoGestaoId())
                .orElseThrow(() -> new EntityNotFoundException("Conselho de Gestão não encontrado com ID: " + condominioDTO.getConselhoGestaoId())));
        }

        if (condominioDTO.getConselhoFiscalId() != null) {
            condominio.setConselhoFiscal(conselhoFiscalRepository.findById(condominioDTO.getConselhoFiscalId())
                .orElseThrow(() -> new EntityNotFoundException("Conselho Fiscal não encontrado com ID: " + condominioDTO.getConselhoFiscalId())));
        }

        if (condominioDTO.getUsuarioAdminId() != null) {
            condominio.setUsuarioAdmin(usuarioRepository.findById(condominioDTO.getUsuarioAdminId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário Admin não encontrado com ID: " + condominioDTO.getUsuarioAdminId())));
        }

        if (condominioDTO.getTorres() != null) {
            condominio.setTorres(condominioDTO.getTorres().stream()
                .map(torreMapperManual::toEntity)
                .collect(Collectors.toSet()));
        }

        return condominio;
    }

    public CondominioDTO toDTO(Condominio condominio) {
        CondominioDTO condominioDTO = new CondominioDTO();
        condominioDTO.setId(condominio.getId());
        condominioDTO.setNome(condominio.getNome());
        condominioDTO.setEndereco(condominio.getEndereco());
        condominioDTO.setCnpj(condominio.getCnpj());

        if (condominio.getSindico() != null) {
            condominioDTO.setSindicoId(condominio.getSindico().getId());
        }

        if (condominio.getConselhoGestao() != null) {
            condominioDTO.setConselhoGestaoId(condominio.getConselhoGestao().getId());
        }

        if (condominio.getConselhoFiscal() != null) {
            condominioDTO.setConselhoFiscalId(condominio.getConselhoFiscal().getId());
        }

        if (condominio.getUsuarioAdmin() != null) {
            condominioDTO.setUsuarioAdminId(condominio.getUsuarioAdmin().getId());
        }

        if (condominio.getTorres() != null) {
            condominioDTO.setTorres(condominio.getTorres().stream()
                .map(torreMapperManual::toDTO)
                .collect(Collectors.toSet()));
        }

        return condominioDTO;
    }
}
