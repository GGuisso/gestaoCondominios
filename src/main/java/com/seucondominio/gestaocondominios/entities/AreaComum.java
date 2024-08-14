package com.seucondominio.gestaocondominios.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "area_comum")
public class AreaComum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 4000)
    private String descricao;

    @Column(nullable = false, length = 4000)
    private String regrasUso;

    @Column(nullable = false, length = 50)
    private String tipoAgendamento;

    @ManyToOne
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;
}
