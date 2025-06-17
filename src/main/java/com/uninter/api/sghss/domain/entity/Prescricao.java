package com.uninter.api.sghss.domain.entity;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescricoes")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicamento;

    private String posologia; // de 12 em 12 horas

    private String observacoes; // opcional, ex: "tomar com alimentos"

    @Setter
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    private LocalDateTime criadoEm;

    public Prescricao(String medicamento, String posologia, String observacoes){
        this.medicamento = medicamento;
        this.posologia = posologia;
        this.observacoes = observacoes;
        this.criadoEm = LocalDateTime.now();
    }

}
