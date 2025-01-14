package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.TorreDTO;
import com.seucondominio.gestaocondominios.dto.UnidadeDTO;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.entities.Torre;
import com.seucondominio.gestaocondominios.entities.Unidade;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TorreMapperManual {

    private final CondominioRepository condominioRepository;

    public TorreMapperManual(CondominioRepository condominioRepository) {
        this.condominioRepository = condominioRepository;
    }

    public Torre toEntity(TorreDTO torreDTO) {
        Torre torre = new Torre();
        torre.setId(torreDTO.getId());
        torre.setNome(torreDTO.getNome());

        // Associa a torre ao condomínio correspondente
        if (torreDTO.getCondominioId() != null) {
            Condominio condominio = condominioRepository.findById(torreDTO.getCondominioId())
                .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + torreDTO.getCondominioId()));
            torre.setCondominio(condominio);
        }

        if (torreDTO.getUnidades() != null) {
            torre.setUnidades(torreDTO.getUnidades().stream()
                .map(this::toEntityUnidade)
                .collect(Collectors.toSet()));
        }

        return torre;
    }

    public TorreDTO toDTO(Torre torre) {
        TorreDTO torreDTO = new TorreDTO();
        torreDTO.setId(torre.getId());
        torreDTO.setNome(torre.getNome());
        torreDTO.setCondominioId(torre.getCondominio().getId());

        if (torre.getUnidades() != null) {
            torreDTO.setUnidades(torre.getUnidades().stream()
                .map(this::toDTOUnidade)
                .collect(Collectors.toSet()));
        }

        return torreDTO;
    }

    private Unidade toEntityUnidade(UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        unidade.setId(unidadeDTO.getId());
        unidade.setNumero(unidadeDTO.getNumero());
        return unidade;
    }

    private UnidadeDTO toDTOUnidade(Unidade unidade) {
        UnidadeDTO unidadeDTO = new UnidadeDTO();
        unidadeDTO.setId(unidade.getId());
        unidadeDTO.setNumero(unidade.getNumero());
        unidadeDTO.setTorreId(unidade.getTorre().getId());
        return unidadeDTO;
    }
}
