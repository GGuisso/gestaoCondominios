package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.TorreDTO;
import java.util.List;

public interface ITorreService {
    TorreDTO saveTorre(TorreDTO torreDTO);
    TorreDTO updateTorre(Long id, TorreDTO torreDTO);
    TorreDTO getTorreById(Long id);
    List<TorreDTO> getAllTorres();
    void deleteTorre(Long id);
}
