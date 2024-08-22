package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import java.util.List;

public interface ICondominioService {
    CondominioDTO saveCondominio(CondominioDTO condominioDTO);
    CondominioDTO updateCondominio(Long id, CondominioDTO condominioDTO);
    CondominioDTO getCondominioById(Long id);
    CondominioDTO getCondominioByCnpj(String cnpj);
    List<CondominioDTO> getAllCondominios();
    void deleteCondominio(Long id);
}

