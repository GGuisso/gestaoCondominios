package com.seucondominio.gestaocondominios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "morador")
public class Morador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String sobrenome;  // Novo campo

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;  // Novo campo

    @Column(length = 50)
    private String unidade;

    @Column(length = 20)
    private String telefone;

    @Column(length = 255)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conselho_gestao_id")
    private ConselhoGestao conselhoGestao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conselho_fiscal_id")
    private ConselhoFiscal conselhoFiscal;
}
