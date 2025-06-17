package com.uninter.api.sghss.domain.dto.response;

import java.time.LocalDateTime;

public record PrescricaoResponseDTO(
        Long id,
        String medicamento,
        String posologia,
        String observacoes,
        LocalDateTime criadoEm,
        Long consultaId
) {
}
