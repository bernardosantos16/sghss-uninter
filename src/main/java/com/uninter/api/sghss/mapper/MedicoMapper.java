package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.MedicoResponseDTO;
import com.uninter.api.sghss.domain.entity.Medico;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MedicoMapper {

    MedicoResponseDTO medicoToMedicoResponseDTO(Medico medico);
}
