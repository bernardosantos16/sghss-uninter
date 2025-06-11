package com.uninter.api.sghss.domain.dto.request;

public record UpdateRequestMedicoDTO(
        Long id,
        String nome,
        String telefone,
        EnderecoRequestDTO endereco
) {
}
