package com.uninter.api.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum StatusConsulta {
    PENDENTE("Pendente"),
    CANCELADA("Cancelada"),
    REALIZADA("Realizada"),;

    private final String descricao;

    StatusConsulta(String descricao) {
        this.descricao = descricao;
    }
}
