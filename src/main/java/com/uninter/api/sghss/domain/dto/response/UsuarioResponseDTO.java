package com.uninter.api.sghss.domain.dto.response;

import com.uninter.api.sghss.domain.enums.UserRole;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String login,
        UserRole userRole
) {
}
