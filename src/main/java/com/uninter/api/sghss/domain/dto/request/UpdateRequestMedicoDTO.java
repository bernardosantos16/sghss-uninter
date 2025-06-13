package com.uninter.api.sghss.domain.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public record UpdateRequestMedicoDTO(
        Long id,
        String nome,
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
        String telefone,
        @Valid
        EnderecoRequestDTO endereco
) {
}
