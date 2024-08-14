package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.AreaComumDTO;
import com.seucondominio.gestaocondominios.entities.AreaComum;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaComumMapperManual {

    @Autowired
    private CondominioRepository condominioRepository;

    public AreaComum toEntity(AreaComumDTO areaComumDTO) {
        AreaComum areaComum = new AreaComum();
        areaComum.setId(areaComumDTO.getId());
        areaComum.setNome(areaComumDTO.getNome());
        areaComum.setDescricao(areaComumDTO.getDescricao());
        areaComum.setRegrasUso(areaComumDTO.getRegrasUso());
        areaComum.setTipoAgendamento(areaComumDTO.getTipoAgendamento());

        // Mapeando Condominio
        Condominio condominio = condominioRepository.findById(areaComumDTO.getCondominioId())
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + areaComumDTO.getCondominioId()));
        areaComum.setCondominio(condominio);

        return areaComum;
    }

    public AreaComumDTO toDTO(AreaComum areaComum) {
        AreaComumDTO areaComumDTO = new AreaComumDTO();
        areaComumDTO.setId(areaComum.getId());
        areaComumDTO.setNome(areaComum.getNome());
        areaComumDTO.setDescricao(areaComum.getDescricao());
        areaComumDTO.setRegrasUso(areaComum.getRegrasUso());
        areaComumDTO.setTipoAgendamento(areaComum.getTipoAgendamento());
        areaComumDTO.setCondominioId(areaComum.getCondominio().getId());
        return areaComumDTO;
    }
}
