package com.uninter.api.sghss.domain.dto.request;

import com.uninter.api.sghss.domain.enums.UserRole;
import jakarta.persistence.Enumerated;

public record RegisterDTO(
        String login,
        String senha

        //UserRole userRole
) {
}
