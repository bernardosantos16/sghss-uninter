package com.uninter.api.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Administrador"),
    MEDICO("Médico"),
    USUARIO("Usuario");

    private final String descricao;

    UserRole(String descricao) {
        this.descricao = descricao;
    }

}
