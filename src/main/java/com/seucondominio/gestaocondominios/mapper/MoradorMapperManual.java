package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import com.seucondominio.gestaocondominios.entities.Morador;
import org.springframework.stereotype.Component;

@Component
public class MoradorMapperManual {

    public Morador toEntity(MoradorDTO moradorDTO) {
        Morador morador = new Morador();
        morador.setId(moradorDTO.getId());
        morador.setNome(moradorDTO.getNome());
        morador.setSobrenome(moradorDTO.getSobrenome());  // Novo campo
        morador.setCpf(moradorDTO.getCpf());  // Novo campo
        morador.setUnidade(moradorDTO.getUnidade());
        morador.setTelefone(moradorDTO.getTelefone());
        morador.setEmail(moradorDTO.getEmail());
        return morador;
    }

    public MoradorDTO toDTO(Morador morador) {
        MoradorDTO moradorDTO = new MoradorDTO();
        moradorDTO.setId(morador.getId());
        moradorDTO.setNome(morador.getNome());
        moradorDTO.setSobrenome(morador.getSobrenome());  // Novo campo
        moradorDTO.setCpf(morador.getCpf());  // Novo campo
        moradorDTO.setUnidade(morador.getUnidade());
        moradorDTO.setTelefone(morador.getTelefone());
        moradorDTO.setEmail(morador.getEmail());

        if (morador.getCondominio() != null) {
            moradorDTO.setCondominioId(morador.getCondominio().getId());
        }
        if (morador.getConselhoGestao() != null) {
            moradorDTO.setConselhoGestaoId(morador.getConselhoGestao().getId());
        }
        if (morador.getConselhoFiscal() != null) {
            moradorDTO.setConselhoFiscalId(morador.getConselhoFiscal().getId());
        }
        
        return moradorDTO;
    }
}
