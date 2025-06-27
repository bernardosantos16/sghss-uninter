package com.uninter.api.sghss.controller;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;
import com.uninter.api.sghss.domain.dto.response.PrescricaoResponseDTO;
import com.uninter.api.sghss.service.PrescricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoController {

    @Autowired
    private PrescricaoService prescricaoService;

    @PostMapping
    public ResponseEntity<PrescricaoResponseDTO> criarPrescricao(
            @RequestBody
            @Valid
            PrescricaoRequestDTO prescricaoRequestDTO
    ){
        var response = prescricaoService.criarPrescriacao(prescricaoRequestDTO);
        return ResponseEntity.ok(response);
    }
}
