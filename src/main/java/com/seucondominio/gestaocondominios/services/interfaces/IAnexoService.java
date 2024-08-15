package com.seucondominio.gestaocondominios.services.interfaces;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;

import java.util.List;

public interface IAnexoService {
    AnexoDTO saveAnexo(AnexoDTO anexoDTO);
    List<AnexoDTO> getAnexosByChamadoId(Long chamadoId);
    void deleteAnexo(Long id);
}
