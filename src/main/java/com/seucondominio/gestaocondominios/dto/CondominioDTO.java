package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CondominioDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;
    private Long sindicoId;
    private Long conselhoGestaoId;
    private Long conselhoFiscalId;
    private Long usuarioAdminId;
    private Set<TorreDTO> torres;  // Campo para incluir as torres
}
