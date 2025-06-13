package com.uninter.api.sghss.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UpdateRequestPacienteDTO(
        String nome,
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy") @Past
        LocalDate dataNascimento,
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
        String telefone,
        @Email
        String email,
        @Valid
        EnderecoRequestDTO endereco
) {
}
