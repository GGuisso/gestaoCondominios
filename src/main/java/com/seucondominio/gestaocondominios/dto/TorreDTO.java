package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorreDTO {
    private Long id;
    private String nome;
    private Long condominioId;
    private Set<UnidadeDTO> unidades;
}
