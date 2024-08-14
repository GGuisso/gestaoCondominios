package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import com.seucondominio.gestaocondominios.entities.Condominio;
import org.springframework.stereotype.Component;

@Component
public class CondominioMapperManual {

    public Condominio toEntity(CondominioDTO condominioDTO) {
        Condominio condominio = new Condominio();
        condominio.setId(condominioDTO.getId());
        condominio.setNome(condominioDTO.getNome());
        condominio.setEndereco(condominioDTO.getEndereco());
        condominio.setCnpj(condominioDTO.getCnpj());
        // Não mapeando Sindico, ConselhoGestao e ConselhoFiscal aqui, pois eles não estão presentes no DTO
        return condominio;
    }

    public CondominioDTO toDTO(Condominio condominio) {
        CondominioDTO condominioDTO = new CondominioDTO();
        condominioDTO.setId(condominio.getId());
        condominioDTO.setNome(condominio.getNome());
        condominioDTO.setEndereco(condominio.getEndereco());
        condominioDTO.setCnpj(condominio.getCnpj());
        // Não mapeando Sindico, ConselhoGestao e ConselhoFiscal aqui, pois eles não estão presentes no DTO
        return condominioDTO;
    }
}
