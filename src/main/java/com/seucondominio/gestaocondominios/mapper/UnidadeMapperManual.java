package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.UnidadeDTO;
import com.seucondominio.gestaocondominios.entities.Torre;
import com.seucondominio.gestaocondominios.entities.Unidade;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.TorreRepository;
import org.springframework.stereotype.Component;

@Component
public class UnidadeMapperManual {

    private final TorreRepository torreRepository;

    public UnidadeMapperManual(TorreRepository torreRepository) {
        this.torreRepository = torreRepository;
    }

    public Unidade toEntity(UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        unidade.setId(unidadeDTO.getId());
        unidade.setNumero(unidadeDTO.getNumero());

        if (unidadeDTO.getTorreId() != null) {
            Torre torre = torreRepository.findById(unidadeDTO.getTorreId())
                .orElseThrow(() -> new EntityNotFoundException("Torre não encontrada com ID: " + unidadeDTO.getTorreId()));
            unidade.setTorre(torre);
        }

        return unidade;
    }

    public UnidadeDTO toDTO(Unidade unidade) {
        UnidadeDTO unidadeDTO = new UnidadeDTO();
        unidadeDTO.setId(unidade.getId());
        unidadeDTO.setNumero(unidade.getNumero());

        if (unidade.getTorre() != null) {
            unidadeDTO.setTorreId(unidade.getTorre().getId());
        }

        return unidadeDTO;
    }
}
