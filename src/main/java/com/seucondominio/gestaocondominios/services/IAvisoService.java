package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.AvisoDTO;
import java.util.List;

public interface IAvisoService {
    AvisoDTO saveAviso(AvisoDTO avisoDTO);
    AvisoDTO updateAviso(Long id, AvisoDTO avisoDTO);
    AvisoDTO getAvisoById(Long id);
    List<AvisoDTO> getAllAvisos();
    void deleteAviso(Long id);
}
