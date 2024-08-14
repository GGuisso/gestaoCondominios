package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConselhoGestaoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Long condominioId; // Se necessário, para associar o conselho ao condomínio específico
}
