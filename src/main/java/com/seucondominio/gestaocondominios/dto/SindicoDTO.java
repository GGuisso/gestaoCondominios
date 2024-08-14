package com.seucondominio.gestaocondominios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SindicoDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private boolean profissional;
    private Long condominioId; // ID do condomínio para manter a relação
}
