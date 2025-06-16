package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.domain.dto.request.CancelamentoConsultaDTO;
import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.domain.dto.response.ConsultaDetailedResponseDTO;
import com.uninter.api.sghss.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultaDetailedResponseDTO> agendarConsulta(
            @RequestBody @Valid ConsultaRequestDTO consultaRequestDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        // Implementar a lógica para agendar uma consulta
        var response = consultaService.cadastrarConsulta(consultaRequestDTO);
        var uri = uriComponentsBuilder
                .path("/consultas/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsultaById(@PathVariable Long id) {
        // Implementar a lógica para buscar uma consulta por ID
        var response = consultaService.getConsultaById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultasporpaciente/{id}")
    public ResponseEntity<?> getConsultaPorPaciente(
            @PathVariable Long id,
            @PageableDefault(sort = "data") Pageable pageable) {
        // Implementar a lógica para buscar uma consulta por ID
        var response = consultaService.getConsultaPorPaciente(id, pageable);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelarConsulta(
            @RequestBody @Valid CancelamentoConsultaDTO cancelamentoConsultaDTO){
        consultaService.cancelarConsulta(cancelamentoConsultaDTO);
        return ResponseEntity.noContent().build();
    }
}
