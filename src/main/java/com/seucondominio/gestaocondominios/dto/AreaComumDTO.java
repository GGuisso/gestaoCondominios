package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaComumDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String regrasUso;
    private String tipoAgendamento;
    private Long condominioId; // ID do condomínio para manter a relação
}
