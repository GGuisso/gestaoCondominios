package com.seucondominio.gestaocondominios.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_comum_id", nullable = false)
    private AreaComum areaComum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador morador;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFim;
}
