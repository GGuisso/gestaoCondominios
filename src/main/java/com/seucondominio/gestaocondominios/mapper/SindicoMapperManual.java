package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.SindicoDTO;
import com.seucondominio.gestaocondominios.entities.Sindico;
import org.springframework.stereotype.Component;

@Component
public class SindicoMapperManual {

    public Sindico toEntity(SindicoDTO sindicoDTO) {
        Sindico sindico = new Sindico();
        sindico.setId(sindicoDTO.getId());
        sindico.setNome(sindicoDTO.getNome());
        sindico.setTelefone(sindicoDTO.getTelefone());
        sindico.setEmail(sindicoDTO.getEmail());
        sindico.setProfissional(sindicoDTO.isProfissional());
        return sindico;
    }

    public SindicoDTO toDTO(Sindico sindico) {
        SindicoDTO sindicoDTO = new SindicoDTO();
        sindicoDTO.setId(sindico.getId());
        sindicoDTO.setNome(sindico.getNome());
        sindicoDTO.setTelefone(sindico.getTelefone());
        sindicoDTO.setEmail(sindico.getEmail());
        sindicoDTO.setProfissional(sindico.isProfissional());
        if (sindico.getCondominio() != null) {
            sindicoDTO.setCondominioId(sindico.getCondominio().getId());
        }
        return sindicoDTO;
    }
}
