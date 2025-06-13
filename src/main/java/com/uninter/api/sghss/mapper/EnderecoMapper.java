package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.EnderecoResponseDTO;
import com.uninter.api.sghss.domain.valueobjects.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoResponseDTO enderecoToEnderecoResponseDTO(Endereco endereco);
}
