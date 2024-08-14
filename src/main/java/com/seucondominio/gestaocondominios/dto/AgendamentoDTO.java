package com.seucondominio.gestaocondominios.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {
    private Long id;
    private Long areaComumId;
    private Long moradorId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
}
