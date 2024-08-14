package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.AvisoDTO;
import com.seucondominio.gestaocondominios.entities.Aviso;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvisoMapperManual {

    @Autowired
    private CondominioRepository condominioRepository;

    public Aviso toEntity(AvisoDTO avisoDTO) {
        Aviso aviso = new Aviso();
        aviso.setId(avisoDTO.getId());
        aviso.setTitulo(avisoDTO.getTitulo());
        aviso.setMensagem(avisoDTO.getMensagem());
        aviso.setDataEmissao(avisoDTO.getDataEmissao());

        // Mapeando Condomínio
        Condominio condominio = condominioRepository.findById(avisoDTO.getCondominioId())
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + avisoDTO.getCondominioId()));
        aviso.setCondominio(condominio);

        return aviso;
    }

    public AvisoDTO toDTO(Aviso aviso) {
        AvisoDTO avisoDTO = new AvisoDTO();
        avisoDTO.setId(aviso.getId());
        avisoDTO.setTitulo(aviso.getTitulo());
        avisoDTO.setMensagem(aviso.getMensagem());
        avisoDTO.setDataEmissao(aviso.getDataEmissao());
        avisoDTO.setCondominioId(aviso.getCondominio().getId());
        return avisoDTO;
    }
}
