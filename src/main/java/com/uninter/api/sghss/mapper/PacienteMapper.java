package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.PacienteDetailedResponseDTO;
import com.uninter.api.sghss.domain.entity.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDetailedResponseDTO pacienteToPacienteDetailedResponseDTO(Paciente paciente);
}
