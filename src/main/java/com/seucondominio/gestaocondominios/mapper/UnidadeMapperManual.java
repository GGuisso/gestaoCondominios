package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.UnidadeDTO;
import com.seucondominio.gestaocondominios.entities.Unidade;
import org.springframework.stereotype.Component;

@Component
public class UnidadeMapperManual {

    public Unidade toEntity(UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        unidade.setId(unidadeDTO.getId());
        unidade.setNumero(unidadeDTO.getNumero());
        return unidade;
    }

    public UnidadeDTO toDTO(Unidade unidade) {
        UnidadeDTO unidadeDTO = new UnidadeDTO();
        unidadeDTO.setId(unidade.getId());
        unidadeDTO.setNumero(unidade.getNumero());
        unidadeDTO.setTorreId(unidade.getTorre().getId());
        return unidadeDTO;
    }
}
