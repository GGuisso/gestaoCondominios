package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.ConselhoFiscalDTO;
import com.seucondominio.gestaocondominios.entities.ConselhoFiscal;
import org.springframework.stereotype.Component;

@Component
public class ConselhoFiscalMapperManual {

    public ConselhoFiscal toEntity(ConselhoFiscalDTO conselhoFiscalDTO) {
        ConselhoFiscal conselhoFiscal = new ConselhoFiscal();
        conselhoFiscal.setId(conselhoFiscalDTO.getId());
        conselhoFiscal.setNome(conselhoFiscalDTO.getNome());
        // Se "descricao" for necessário, adicione no ConselhoFiscal
        return conselhoFiscal;
    }

    public ConselhoFiscalDTO toDTO(ConselhoFiscal conselhoFiscal) {
        ConselhoFiscalDTO conselhoFiscalDTO = new ConselhoFiscalDTO();
        conselhoFiscalDTO.setId(conselhoFiscal.getId());
        conselhoFiscalDTO.setNome(conselhoFiscal.getNome());
        // Se "descricao" for necessário, mapeie aqui também
        conselhoFiscalDTO.setCondominioId(conselhoFiscal.getCondominio().getId());
        return conselhoFiscalDTO;
    }
}
