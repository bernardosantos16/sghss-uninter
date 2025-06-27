package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.response.UsuarioResponseDTO;
import com.uninter.api.sghss.domain.entity.Usuario;
import com.uninter.api.sghss.mapper.UsuarioMapper;
import com.uninter.api.sghss.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public void liberarAcessoMedico(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuario.usuarioMedico();
    }

    public UsuarioResponseDTO getUsuarioByLogin(String login) {
        var usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return usuarioMapper.usuarioToUsuarioResponseDTO((Usuario) usuario);

    }
}
