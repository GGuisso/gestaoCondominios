package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataCadastro;
    private String status;
    private LocalDate dataInativacao;
    private List<Long> documentosIds; // IDs dos anexos relacionados ao profissional
}
