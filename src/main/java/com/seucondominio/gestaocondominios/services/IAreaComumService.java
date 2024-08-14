package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.AreaComumDTO;
import java.util.List;

public interface IAreaComumService {
    AreaComumDTO saveAreaComum(AreaComumDTO areaComumDTO);
    AreaComumDTO updateAreaComum(Long id, AreaComumDTO areaComumDTO);
    AreaComumDTO getAreaComumById(Long id);
    List<AreaComumDTO> getAllAreasComuns();
    void deleteAreaComum(Long id);
}
