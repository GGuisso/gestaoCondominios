package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoAgendadoDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String descricao;
    private Long profissionalId;
    private Long condominioId;
    private Long moradorId;
    private List<AnexoDTO> anexos;
}
