package com.uninter.api.sghss.domain.dto.response;

import com.uninter.api.sghss.domain.enums.Especialidade;
import com.uninter.api.sghss.domain.valueobjects.Endereco;

// apenas o provedor de serviços e o proprio medico deve ter acesso a este DTO
// todo: adicionar role de acesso
public record MedicoDetailedResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {

}
