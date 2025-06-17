package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;
import com.uninter.api.sghss.domain.dto.response.PrescricaoResponseDTO;
import com.uninter.api.sghss.domain.entity.Prescricao;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.mapper.PrescricaoMapper;
import com.uninter.api.sghss.repository.ConsultaRepository;
import com.uninter.api.sghss.repository.PrescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescricaoService {
    @Autowired
    PrescricaoRepository prescricaoRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    PrescricaoMapper prescricaoMapper;

    public PrescricaoResponseDTO criarPrescriacao(PrescricaoRequestDTO prescricaoRequestDTO){
        var consulta = consultaRepository.findById(prescricaoRequestDTO.idConsulta())
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
        var prescricao = new Prescricao(
                prescricaoRequestDTO.medicamento(),
                prescricaoRequestDTO.posologia(),
                prescricaoRequestDTO.observacoes()
        );
        consulta.adicionarPrescricao(prescricao);
        prescricaoRepository.save(prescricao);
        return prescricaoMapper.prescricaoToPrescricaoResponseDTO(prescricao);
    }

}
