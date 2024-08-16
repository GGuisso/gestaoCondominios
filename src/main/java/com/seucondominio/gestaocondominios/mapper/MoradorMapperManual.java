package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import com.seucondominio.gestaocondominios.entities.ConselhoFiscal;
import com.seucondominio.gestaocondominios.entities.ConselhoGestao;
import com.seucondominio.gestaocondominios.entities.Morador;
import org.springframework.stereotype.Component;

@Component
public class MoradorMapperManual {

    public Morador toEntity(MoradorDTO moradorDTO) {
        Morador morador = new Morador();
        morador.setId(moradorDTO.getId());
        morador.setNome(moradorDTO.getNome());
        morador.setSobrenome(moradorDTO.getSobrenome()); 
        morador.setCpf(moradorDTO.getCpf()); 
        morador.setTelefone(moradorDTO.getTelefone());
        morador.setEmail(moradorDTO.getEmail());

        if (moradorDTO.getConselhoGestaoId() != null) {
            morador.setConselhoGestao(new ConselhoGestao());
            morador.getConselhoGestao().setId(moradorDTO.getConselhoGestaoId());
        } else {
            morador.setConselhoGestao(null);
        }

        if (moradorDTO.getConselhoFiscalId() != null) {
            morador.setConselhoFiscal(new ConselhoFiscal());
            morador.getConselhoFiscal().setId(moradorDTO.getConselhoFiscalId());
        } else {
            morador.setConselhoFiscal(null);
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

        // Mapeamento dos IDs do Condomínio, Torre, e Unidade, se presentes
        if (morador.getCondominio() != null) {
            moradorDTO.setCondominioId(morador.getCondominio().getId());
        }
        if (morador.getTorre() != null) {
            moradorDTO.setTorreId(morador.getTorre().getId());
        }
        if (morador.getUnidade() != null) {
            moradorDTO.setUnidadeId(morador.getUnidade().getId());
        }

        // Mapeamento dos Conselhos, se presentes
        if (morador.getConselhoGestao() != null) {
            moradorDTO.setConselhoGestaoId(morador.getConselhoGestao().getId());
        }
        if (morador.getConselhoFiscal() != null) {
            moradorDTO.setConselhoFiscalId(morador.getConselhoFiscal().getId());
        }

        return moradorDTO;
    }
}
