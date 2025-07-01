package com.uninter.api.sghss.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Long idEspecialidade,
        String observacoes
) {
}
