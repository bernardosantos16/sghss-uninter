package com.uninter.api.sghss.domain.dto.request;

import com.uninter.api.sghss.domain.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoRequestDTO(
        @NotBlank
        String nome,

        @NotBlank @Email
        String email,

        @NotBlank @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX") // validar o formato do telefone (ex: (XX) XXXX-XXXX ou (XX) XXXXX-XXXX)
        String telefone,

        @NotBlank @Pattern(regexp = "\\d{4,6}", message = "CRM inválido") // validar o formato do CRM (4 a 6 dígitos)
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull @Valid
        EnderecoRequestDTO endereco
) {
}
