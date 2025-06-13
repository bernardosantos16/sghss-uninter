package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.EnderecoResponseDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoDetailedResponseDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoResponseDTO;
import com.uninter.api.sghss.domain.entity.Medico;
import com.uninter.api.sghss.domain.valueobjects.Endereco;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MedicoMapper {

    MedicoDetailedResponseDTO medicoToMedicoDetailedResponseDTO(Medico medico);

    MedicoResponseDTO medicoToMedicoResponseDTO(Medico medico);

}
