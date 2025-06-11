package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestMedicoDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoDetailedResponseDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoResponseDTO;
import com.uninter.api.sghss.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetailedResponseDTO> cadastrarMedico(
            @RequestBody @Valid MedicoRequestDTO medicoRequestDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var response = medicoService.cadastrarMedico(medicoRequestDTO);
        var uri = uriComponentsBuilder
                .path("/medicos/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetailedResponseDTO> getMedicoById(@PathVariable Long id) {
        var response = medicoService.getMedicoById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> getListMedicos() {
        var response = medicoService.getListMedicos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoDetailedResponseDTO> updateMedico(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRequestMedicoDTO updateRequestMedicoDTO
    ){
        var response = medicoService.updateMedico(id, updateRequestMedicoDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativarMedico(@PathVariable Long id) {
        medicoService.inativarMedico(id);
        return ResponseEntity.noContent().build();
    }

}
