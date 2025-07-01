package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.domain.dto.request.EspecialidadeRequestDTO;
import com.uninter.api.sghss.service.EspecialidadeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    EspecialidadeService especialidadeService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrarEspecialidade(
            @RequestBody EspecialidadeRequestDTO especialidadeRequestDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var response = especialidadeService.cadastrarEspecialidade(especialidadeRequestDTO);
        var uri = uriComponentsBuilder.path("/especialidades/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEspecialidades(@PathVariable Long id) {
        var response = especialidadeService.getEspecialidadeById(id);
        return ResponseEntity.ok(response);
        // Implement the method to handle the request for retrieving a list of specialties
        // This is a placeholder method; actual implementation will depend on the response requirements
    }
}
