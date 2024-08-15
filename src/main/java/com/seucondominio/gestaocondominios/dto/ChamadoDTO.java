package com.seucondominio.gestaocondominios.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoDTO {
    private Long id;
    private Long moradorId;           // ID do Morador associado ao chamado
    private Long sindicoId;           // ID do Sindico que está atendendo o chamado
    private String descricao;
    private String tipo;              // Pode ser 'Solicitação' ou 'Incidente'
    private String status;            // Pode ser 'Aberto', 'Em Progresso', 'Concluído'
    private String descricaoAtendimento;
    private String descricaoSolucao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private List<AnexoDTO> anexos;    // Lista de Anexos associados ao chamado
}
