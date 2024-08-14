package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConselhoFiscalDTO {
    private Long id;
    private String nome;
    private String descricao; // Esse campo não está na entidade, então deve ser adicionado se necessário
    private Long condominioId; // ID do condomínio para manter a relação
}
