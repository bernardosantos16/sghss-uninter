package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.PrescricaoResponseDTO;
import com.uninter.api.sghss.domain.entity.Prescricao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescricaoMapper {
    @Mapping(target = "consultaId", source = "consulta.id")
    PrescricaoResponseDTO prescricaoToPrescricaoResponseDTO(Prescricao prescricao);
}
