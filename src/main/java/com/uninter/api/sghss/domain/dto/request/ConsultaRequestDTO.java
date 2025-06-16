package com.uninter.api.sghss.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uninter.api.sghss.domain.enums.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        Long idMedico,
        Long idPaciente,

        @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade,
        String observacoes
) {
}
