package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.SindicoDTO;
import java.util.List;

public interface ISindicoService {
    SindicoDTO saveSindico(SindicoDTO sindicoDTO);
    SindicoDTO updateSindico(Long id, SindicoDTO sindicoDTO);
    SindicoDTO getSindicoById(Long id);
    List<SindicoDTO> getAllSindicos();
    void deleteSindico(Long id);
}
