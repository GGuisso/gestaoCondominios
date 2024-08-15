package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.ConselhoGestaoDTO;
import java.util.List;

public interface IConselhoGestaoService {
    ConselhoGestaoDTO saveConselhoGestao(ConselhoGestaoDTO conselhoGestaoDTO);
    ConselhoGestaoDTO updateConselhoGestao(Long id, ConselhoGestaoDTO conselhoGestaoDTO);
    ConselhoGestaoDTO getConselhoGestaoById(Long id);
    List<ConselhoGestaoDTO> getAllConselhosGestao();
    void deleteConselhoGestao(Long id);
}
