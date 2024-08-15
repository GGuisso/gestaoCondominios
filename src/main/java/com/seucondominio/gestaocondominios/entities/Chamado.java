package com.seucondominio.gestaocondominios.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chamado")
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador morador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sindico_id")
    private Sindico sindicoAtendente;

    @Column(nullable = false, length = 4000)
    private String descricao;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 50)
    private String status; // Aberto, Em atendimento, Resolvido

    @Column(length = 4000)
    private String descricaoAtendimento;

    @Column(length = 4000)
    private String descricaoSolucao;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    @Column
    private LocalDateTime dataFechamento;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AnexoChamado> anexos;
}
