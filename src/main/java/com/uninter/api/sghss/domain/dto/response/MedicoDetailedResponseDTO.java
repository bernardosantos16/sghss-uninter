package com.uninter.api.sghss.domain.dto.response;

// apenas o provedor de serviços e o proprio medico deve ter acesso a este DTO
public record MedicoDetailedResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Long idEspecialidade,
        EnderecoResponseDTO endereco
) {
}
