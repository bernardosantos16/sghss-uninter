package com.uninter.api.sghss.mapper;

import com.uninter.api.sghss.domain.dto.response.UsuarioResponseDTO;
import com.uninter.api.sghss.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "userRole", source = "usuario.userRole")
    UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario);
}
