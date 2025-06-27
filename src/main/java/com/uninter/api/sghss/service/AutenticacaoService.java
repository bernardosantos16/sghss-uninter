package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.RegisterDTO;
import com.uninter.api.sghss.domain.dto.response.UsuarioResponseDTO;
import com.uninter.api.sghss.domain.entity.Usuario;
import com.uninter.api.sghss.mapper.UsuarioMapper;
import com.uninter.api.sghss.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public boolean usuarioExiste(String login) {
        return usuarioRepository.existsByLogin(login);
    }

    public void registrarUsuario(RegisterDTO registerDTO) {
        if (usuarioExiste(registerDTO.login())) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        var senhaCriptografada = new BCryptPasswordEncoder().encode(registerDTO.senha());
        Usuario usuario = new Usuario(registerDTO.login(), senhaCriptografada);
        usuarioRepository.save(usuario);
    }


}
