package com.uninter.api.sghss.domain.dto.response;

import com.uninter.api.sghss.domain.entity.Medico;
import com.uninter.api.sghss.domain.enums.Especialidade;
import com.uninter.api.sghss.domain.valueobjects.Endereco;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    // para returns no service pode ser usado esse constructor ou um mapper
    public MedicoResponseDTO(Medico medico){
        this(
            medico.getId(),
            medico.getNome(),
            medico.getEmail(),
            medico.getTelefone(),
            medico.getCrm(),
            medico.getEspecialidade(),
            medico.getEndereco()
        );
    }
}
