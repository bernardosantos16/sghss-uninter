package com.uninter.api.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum Especialidade {
    CLINICA_GERAL("Clínica Geral"),
    PEDIATRIA("Pediatria"),
    GINECOLOGIA("Ginecologia"),
    ORTOPEDIA("Ortopedia"),
    CARDIOLOGIA("Cardiologia"),
    DERMATOLOGIA("Dermatologia"),
    PSIQUIATRIA("Psiquiatria"),
    OFTALMOLOGIA("Oftalmologia");

    private final String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }

}
