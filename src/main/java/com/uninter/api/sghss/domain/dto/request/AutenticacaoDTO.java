package com.uninter.api.sghss.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
    @NotBlank String login,
    @NotBlank String senha
) {
}
