package com.uninter.api.sghss.domain.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrescricaoRequestDTO(
        @NotNull
        Long idConsulta,
        @NotBlank
        String medicamento,
        String posologia,
        String observacoes
) {
}
