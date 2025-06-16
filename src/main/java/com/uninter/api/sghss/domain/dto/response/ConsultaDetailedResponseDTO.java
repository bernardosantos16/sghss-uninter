package com.uninter.api.sghss.domain.dto.response;

import com.uninter.api.sghss.domain.enums.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaDetailedResponseDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        StatusConsulta statusConsulta
) {
}
