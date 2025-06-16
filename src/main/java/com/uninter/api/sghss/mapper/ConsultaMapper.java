package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.ConsultaDetailedResponseDTO;
import com.uninter.api.sghss.domain.entity.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    @Mapping(target = "idPaciente", source = "paciente.id")
    @Mapping(target = "idMedico", source = "medico.id")
    ConsultaDetailedResponseDTO consultaToConsultaDetailedResponseDTO(Consulta consulta);
}
