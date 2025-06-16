package com.uninter.api.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum MotivoCancelamento {
    PACIENTE_CANCELOU("Paciente cancelou a consulta"),
    MEDICO_CANCELOU("Médico cancelou a consulta"),
    OUTRO("Outro motivo de cancelamento");

    private final String descricao;

    public MotivoCancelamento fromString(String value) {
        for (MotivoCancelamento motivo : MotivoCancelamento.values()) {
            if (motivo.descricao.equalsIgnoreCase(value)) {
                return motivo;
            }
        }
        throw new IllegalArgumentException("Nenhum motivo de cancelamento encontrado com a descrição: " + value);
    }

    MotivoCancelamento(String descricao) {
        this.descricao = descricao;
    }
}

