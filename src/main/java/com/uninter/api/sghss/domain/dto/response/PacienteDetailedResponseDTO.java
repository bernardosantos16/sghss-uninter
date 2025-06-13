package com.uninter.api.sghss.domain.dto.response;

import com.uninter.api.sghss.domain.dto.request.EnderecoRequestDTO;
import com.uninter.api.sghss.domain.enums.Sexo;
import com.uninter.api.sghss.domain.valueobjects.Endereco;

public record PacienteDetailedResponseDTO(
        Long id,
        String nome,
        String cpf,
        String dataNascimento,
        String telefone,
        String email,
        Sexo sexo,
        Endereco endereco


) {
}
