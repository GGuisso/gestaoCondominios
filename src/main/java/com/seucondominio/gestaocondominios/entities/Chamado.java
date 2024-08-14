package com.seucondominio.gestaocondominios.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
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

    @Column(nullable = false, length = 4000)
    private String descricao;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    @Column
    private LocalDateTime dataFechamento;
}
