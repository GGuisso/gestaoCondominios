package com.seucondominio.gestaocondominios.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvisoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataEmissao;
    private Long condominioId;  // ID do Condomínio associado ao aviso
}
