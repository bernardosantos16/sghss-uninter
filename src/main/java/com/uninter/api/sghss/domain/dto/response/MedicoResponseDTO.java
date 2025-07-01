package com.uninter.api.sghss.domain.dto.response;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Long idEspecialidade
) {
}