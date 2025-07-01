package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.EspecialidadeResponseDTO;
import com.uninter.api.sghss.domain.entity.Especialidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspecialidadeMapper {

    EspecialidadeResponseDTO especialidadeToEspecialidadeResponseDTO(Especialidade especialidade);

    List<EspecialidadeResponseDTO> toDtoList(List<Especialidade> especialidades);
}
