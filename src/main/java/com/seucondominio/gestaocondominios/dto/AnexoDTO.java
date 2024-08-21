package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnexoDTO {
    private Long id;
    private String nome;
    private String caminhoArquivo;
    private String descricao;
    private Long chamadoId;
    private Long servicoAgendadoId;
    private Long profissionalId; 
}
