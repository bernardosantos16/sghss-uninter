package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/liberaracessomedico/{id}")
    @Transactional
    public ResponseEntity<?> liberarAcessoMedico(@PathVariable UUID id) {
        usuarioService.liberarAcessoMedico(id);
        return ResponseEntity.ok("Acesso liberado com sucesso");
    }

    @GetMapping("/{login}")
    public ResponseEntity<?> getUsuarioByLogin(@PathVariable String login) {
        var usuario = usuarioService.getUsuarioByLogin(login);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
}
