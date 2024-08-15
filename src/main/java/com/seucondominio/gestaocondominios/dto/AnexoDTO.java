package com.seucondominio.gestaocondominios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnexoDTO {
    private Long id;
    private Long chamadoId;      // ID do Chamado associado ao anexo
    private String nomeArquivo;
    private String caminhoArquivo;
    private LocalDateTime dataUpload;
    private Long moradorId;      // ID do Morador que fez o upload (opcional)
    private Long sindicoId;      // ID do Sindico que fez o upload (opcional)
}
