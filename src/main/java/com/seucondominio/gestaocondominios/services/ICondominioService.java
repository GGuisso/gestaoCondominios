package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import java.util.List;

public interface ICondominioService {
    CondominioDTO saveCondominio(CondominioDTO condominioDTO);
    CondominioDTO updateCondominio(Long id, CondominioDTO condominioDTO);
    CondominioDTO getCondominioById(Long id);
    List<CondominioDTO> getAllCondominios();
    void deleteCondominio(Long id);
}

