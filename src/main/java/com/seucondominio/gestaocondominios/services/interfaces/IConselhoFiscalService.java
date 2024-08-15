package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.ConselhoFiscalDTO;
import java.util.List;

public interface IConselhoFiscalService {
    ConselhoFiscalDTO saveConselhoFiscal(ConselhoFiscalDTO conselhoFiscalDTO);
    ConselhoFiscalDTO updateConselhoFiscal(Long id, ConselhoFiscalDTO conselhoFiscalDTO);
    ConselhoFiscalDTO getConselhoFiscalById(Long id);
    List<ConselhoFiscalDTO> getAllConselhosFiscais();
    void deleteConselhoFiscal(Long id);
}
