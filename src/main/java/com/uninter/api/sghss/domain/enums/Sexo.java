package com.uninter.api.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum Sexo {
    M("Masculino"),
    F("Feminino");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

}
