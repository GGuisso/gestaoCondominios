package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.ConselhoGestaoDTO;
import com.seucondominio.gestaocondominios.entities.ConselhoGestao;
import org.springframework.stereotype.Component;

@Component
public class ConselhoGestaoMapperManual {

    public ConselhoGestao toEntity(ConselhoGestaoDTO conselhoGestaoDTO) {
        ConselhoGestao conselhoGestao = new ConselhoGestao();
        conselhoGestao.setId(conselhoGestaoDTO.getId());
        conselhoGestao.setNome(conselhoGestaoDTO.getNome());
        // Se "descricao" for necessário, adicione no ConselhoGestao
        return conselhoGestao;
    }

    public ConselhoGestaoDTO toDTO(ConselhoGestao conselhoGestao) {
        ConselhoGestaoDTO conselhoGestaoDTO = new ConselhoGestaoDTO();
        conselhoGestaoDTO.setId(conselhoGestao.getId());
        conselhoGestaoDTO.setNome(conselhoGestao.getNome());
        // Se "descricao" for necessário, mapeie aqui também
        conselhoGestaoDTO.setCondominioId(conselhoGestao.getCondominio().getId());
        return conselhoGestaoDTO;
    }
}
