package com.uninter.api.sghss.domain.dto.request;

import com.uninter.api.sghss.domain.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record CancelamentoRequestConsultaDTO(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
