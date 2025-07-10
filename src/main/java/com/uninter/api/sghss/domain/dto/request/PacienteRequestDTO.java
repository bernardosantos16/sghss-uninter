package com.uninter.api.sghss.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uninter.api.sghss.domain.enums.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PacienteRequestDTO(
        @NotBlank
        String nome,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,

        @NotNull @Past @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @NotNull
        Sexo sexo,

        @NotBlank @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
        String telefone,

        @Email
        String email,

        @NotNull @Valid
        EnderecoRequestDTO endereco
) {

}
