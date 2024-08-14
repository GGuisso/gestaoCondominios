package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CondominioDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;
}
