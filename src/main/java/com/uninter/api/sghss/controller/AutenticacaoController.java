package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.domain.dto.request.AutenticacaoDTO;
import com.uninter.api.sghss.domain.dto.request.RegisterDTO;
import com.uninter.api.sghss.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(
                autenticacaoDTO.login(),
                autenticacaoDTO.senha()
        );
        var auth = authenticationManager.authenticate(autenticationToken);
        return ResponseEntity.ok(auth.getPrincipal());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (autenticacaoService.usuarioExiste(registerDTO.login())){
            return ResponseEntity.badRequest().body("Usuário já existe");
        }
        autenticacaoService.registrarUsuario(registerDTO);
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PutMapping("/liberaracessomedico/{id}")
    public ResponseEntity<?> liberarAcessoMedico(@PathVariable UUID id) {
        autenticacaoService.liberarAcessoMedico(id);
        return ResponseEntity.ok("Acesso liberado com sucesso");
    }
}
