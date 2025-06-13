package com.uninter.api.sghss.domain.dto.response;

public record EnderecoResponseDTO(
        String logradouro,
        String bairro,
        String cep,
        String numero,
        String complemento,
        String cidade,
        String uf
) {
}
