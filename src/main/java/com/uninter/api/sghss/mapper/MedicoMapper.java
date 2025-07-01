package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.MedicoDetailedResponseDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoResponseDTO;
import com.uninter.api.sghss.domain.entity.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MedicoMapper {


    @Mapping(target = "idEspecialidade", source = "especialidade.id")
    MedicoDetailedResponseDTO medicoToMedicoDetailedResponseDTO(Medico medico);


    @Mapping(target = "idEspecialidade", source = "especialidade.id")
    MedicoResponseDTO medicoToMedicoResponseDTO(Medico medico);

}
