package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.domain.dto.request.AutenticacaoDTO;
import com.uninter.api.sghss.domain.dto.request.RegisterDTO;
import com.uninter.api.sghss.domain.dto.response.JWTResposeDTO;
import com.uninter.api.sghss.domain.entity.Usuario;
import com.uninter.api.sghss.infra.security.TokenService;
import com.uninter.api.sghss.service.AutenticacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(
                autenticacaoDTO.login(),
                autenticacaoDTO.senha()
        );
        var auth = authenticationManager.authenticate(autenticationToken);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new JWTResposeDTO(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (autenticacaoService.usuarioExiste(registerDTO.login())){
            return ResponseEntity.badRequest().body("Usuário já existe");
        }
        autenticacaoService.registrarUsuario(registerDTO);
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }


}
