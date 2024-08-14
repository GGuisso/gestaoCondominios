package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoradorDTO {
    private Long id;
    private String nome;
    private String unidade;
    private String telefone;
    private String email;
    private Long condominioId;       // ID do Condomínio
    private Long conselhoGestaoId;   // ID do Conselho de Gestão
    private Long conselhoFiscalId;   // ID do Conselho Fiscal
}
