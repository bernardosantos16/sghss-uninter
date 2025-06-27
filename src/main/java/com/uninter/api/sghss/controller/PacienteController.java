package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.domain.dto.request.PacienteRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestPacienteDTO;
import com.uninter.api.sghss.domain.dto.response.PacienteDetailedResponseDTO;
import com.uninter.api.sghss.service.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDetailedResponseDTO> cadastrarPaciente(
            @RequestBody @Valid PacienteRequestDTO pacienteRequestDTO,
            UriComponentsBuilder uriComponentsBuilder) {
            var response = pacienteService.cadastrarPaciente(pacienteRequestDTO);
            var uri = uriComponentsBuilder
                    .path("/pacientes/{id}")
                    .buildAndExpand(response.id())
                    .toUri();
            return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDetailedResponseDTO> getPacienteById(@PathVariable Long id) {
        var response = pacienteService.getPacienteById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteDetailedResponseDTO> atualizarPaciente(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRequestPacienteDTO updateRequestPacienteDTO) {
        var response = pacienteService.atualizarPaciente(id, updateRequestPacienteDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativarPaciente(@PathVariable Long id) {
        pacienteService.inativarPaciente(id);
        return ResponseEntity.noContent().build();
    }

}
