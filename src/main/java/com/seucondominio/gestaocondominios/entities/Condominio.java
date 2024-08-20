package com.seucondominio.gestaocondominios.entities;

import jakarta.persistence.*;
import java.util.Set;
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
    @JoinColumn(name = "sindico_id", referencedColumnName = "id", nullable = true)
    private Sindico sindico;

    @OneToOne
    @JoinColumn(name = "conselho_gestao_id", referencedColumnName = "id", nullable = true)
    private ConselhoGestao conselhoGestao;

    @OneToOne
    @JoinColumn(name = "conselho_fiscal_id", referencedColumnName = "id", nullable = true)
    private ConselhoFiscal conselhoFiscal;

    @OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Torre> torres;

    @OneToOne
    @JoinColumn(name = "usuario_admin_id", referencedColumnName = "id", nullable = true)
    private Usuario usuarioAdmin; // Relação com o usuário admin do condomínio
}
