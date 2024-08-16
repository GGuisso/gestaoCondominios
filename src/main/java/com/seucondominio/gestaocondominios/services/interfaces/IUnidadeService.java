package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.UnidadeDTO;
import java.util.List;

public interface IUnidadeService {
    UnidadeDTO saveUnidade(UnidadeDTO unidadeDTO);
    UnidadeDTO updateUnidade(Long id, UnidadeDTO unidadeDTO);
    UnidadeDTO getUnidadeById(Long id);
    List<UnidadeDTO> getAllUnidades();
    void deleteUnidade(Long id);
}
