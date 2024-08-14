package com.seucondominio.gestaocondominios.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "condominio")
public class Condominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(length = 255)
    private String endereco;

    @Column(length = 18)
    private String cnpj;

    @OneToOne
    @JoinColumn(name = "sindico_id", referencedColumnName = "id")
    private Sindico sindico;

    @OneToOne
    @JoinColumn(name = "conselho_gestao_id", referencedColumnName = "id")
    private ConselhoGestao conselhoGestao;

    @OneToOne
    @JoinColumn(name = "conselho_fiscal_id", referencedColumnName = "id")
    private ConselhoFiscal conselhoFiscal;
}

