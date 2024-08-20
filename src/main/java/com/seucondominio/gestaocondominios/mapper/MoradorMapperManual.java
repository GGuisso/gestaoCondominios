package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import com.seucondominio.gestaocondominios.entities.*;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.*;
import org.springframework.stereotype.Component;

@Component
public class MoradorMapperManual {

    private final CondominioRepository condominioRepository;
    private final TorreRepository torreRepository;
    private final UnidadeRepository unidadeRepository;
    private final ConselhoGestaoRepository conselhoGestaoRepository;
    private final ConselhoFiscalRepository conselhoFiscalRepository;
    private final UsuarioRepository usuarioRepository;

    public MoradorMapperManual(CondominioRepository condominioRepository, TorreRepository torreRepository, UnidadeRepository unidadeRepository, ConselhoGestaoRepository conselhoGestaoRepository, ConselhoFiscalRepository conselhoFiscalRepository, UsuarioRepository usuarioRepository) {
        this.condominioRepository = condominioRepository;
        this.torreRepository = torreRepository;
        this.unidadeRepository = unidadeRepository;
        this.conselhoGestaoRepository = conselhoGestaoRepository;
        this.conselhoFiscalRepository = conselhoFiscalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Morador toEntity(MoradorDTO moradorDTO) {
        Morador morador = new Morador();
        morador.setId(moradorDTO.getId());
        morador.setNome(moradorDTO.getNome());
        morador.setSobrenome(moradorDTO.getSobrenome());
        morador.setCpf(moradorDTO.getCpf());
        morador.setTelefone(moradorDTO.getTelefone());
        morador.setEmail(moradorDTO.getEmail());

        if (moradorDTO.getCondominioId() != null) {
            morador.setCondominio(condominioRepository.findById(moradorDTO.getCondominioId())
                .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + moradorDTO.getCondominioId())));
        }

        if (moradorDTO.getTorreId() != null) {
            morador.setTorre(torreRepository.findById(moradorDTO.getTorreId())
                .orElseThrow(() -> new EntityNotFoundException("Torre não encontrada com ID: " + moradorDTO.getTorreId())));
        }

        if (moradorDTO.getUnidadeId() != null) {
            morador.setUnidade(unidadeRepository.findById(moradorDTO.getUnidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + moradorDTO.getUnidadeId())));
        }

        if (moradorDTO.getConselhoGestaoId() != null) {
            morador.setConselhoGestao(conselhoGestaoRepository.findById(moradorDTO.getConselhoGestaoId())
                .orElseThrow(() -> new EntityNotFoundException("Conselho de Gestão não encontrado com ID: " + moradorDTO.getConselhoGestaoId())));
        } else {
            morador.setConselhoGestao(null);
        }

        if (moradorDTO.getConselhoFiscalId() != null) {
            morador.setConselhoFiscal(conselhoFiscalRepository.findById(moradorDTO.getConselhoFiscalId())
                .orElseThrow(() -> new EntityNotFoundException("Conselho Fiscal não encontrado com ID: " + moradorDTO.getConselhoFiscalId())));
        } else {
            morador.setConselhoFiscal(null);
        }

        if (moradorDTO.getUsuarioId() != null) {
            morador.setUsuario(usuarioRepository.findById(moradorDTO.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + moradorDTO.getUsuarioId())));
        }

        return morador;
    }

    public MoradorDTO toDTO(Morador morador) {
        MoradorDTO moradorDTO = new MoradorDTO();
        moradorDTO.setId(morador.getId());
        moradorDTO.setNome(morador.getNome());
        moradorDTO.setSobrenome(morador.getSobrenome());
        moradorDTO.setCpf(morador.getCpf());
        moradorDTO.setTelefone(morador.getTelefone());
        moradorDTO.setEmail(morador.getEmail());

        if (morador.getCondominio() != null) {
            moradorDTO.setCondominioId(morador.getCondominio().getId());
        }
        if (morador.getTorre() != null) {
            moradorDTO.setTorreId(morador.getTorre().getId());
        }
        if (morador.getUnidade() != null) {
            moradorDTO.setUnidadeId(morador.getUnidade().getId());
        }
        if (morador.getConselhoGestao() != null) {
            moradorDTO.setConselhoGestaoId(morador.getConselhoGestao().getId());
        }
        if (morador.getConselhoFiscal() != null) {
            moradorDTO.setConselhoFiscalId(morador.getConselhoFiscal().getId());
        }
        if (morador.getUsuario() != null) {
            moradorDTO.setUsuarioId(morador.getUsuario().getId());
        }

        return moradorDTO;
    }
}
