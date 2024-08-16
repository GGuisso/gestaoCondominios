package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import com.seucondominio.gestaocondominios.entities.Condominio;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CondominioMapperManual {

    private final TorreMapperManual torreMapperManual;

    public CondominioMapperManual(TorreMapperManual torreMapperManual) {
        this.torreMapperManual = torreMapperManual;
    }

    public Condominio toEntity(CondominioDTO condominioDTO) {
        Condominio condominio = new Condominio();
        condominio.setId(condominioDTO.getId());
        condominio.setNome(condominioDTO.getNome());
        condominio.setEndereco(condominioDTO.getEndereco());
        condominio.setCnpj(condominioDTO.getCnpj());

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

        if (condominio.getTorres() != null) {
            condominioDTO.setTorres(condominio.getTorres().stream()
                .map(torreMapperManual::toDTO)
                .collect(Collectors.toSet()));
        }

        return condominioDTO;
    }
}
