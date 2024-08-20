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
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String email;
    private Long condominioId;
    private Long torreId;
    private Long unidadeId;
    private Long conselhoGestaoId;
    private Long conselhoFiscalId;
    private Long usuarioId; // Novo campo para manter a relação com o usuário
}
