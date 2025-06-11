package com.uninter.api.sghss.domain.dto.response;

import com.uninter.api.sghss.domain.enums.Especialidade;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
}