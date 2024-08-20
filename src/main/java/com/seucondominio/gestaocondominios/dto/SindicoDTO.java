package com.seucondominio.gestaocondominios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SindicoDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String email;
    private boolean profissional;
    private Long usuarioId; // ID do usuário associado
    private Set<Long> condominioIds; // IDs dos condomínios que ele gerencia
}
