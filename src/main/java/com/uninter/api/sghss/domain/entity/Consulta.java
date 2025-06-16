package com.uninter.api.sghss.domain.entity;

import com.uninter.api.sghss.domain.enums.MotivoCancelamento;
import com.uninter.api.sghss.domain.enums.StatusConsulta;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="consultas")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL pode ser usado, mas STRING é mais legível e seguro
    private StatusConsulta statusConsulta;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    private LocalDateTime dataCancelamento;

    private String observacoes;

    public Consulta(Medico medico, Paciente paciente, LocalDateTime data, String observacoes) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.statusConsulta = StatusConsulta.PENDENTE;
        this.observacoes = observacoes;
    }

    public void cancelar(MotivoCancelamento motivoCancelamento) {
        this.statusConsulta = StatusConsulta.CANCELADA;
        this.motivoCancelamento = motivoCancelamento;
        this.dataCancelamento = LocalDateTime.now();
    }
}

