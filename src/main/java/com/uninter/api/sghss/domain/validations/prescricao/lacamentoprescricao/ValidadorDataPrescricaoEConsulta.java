package com.uninter.api.sghss.domain.validations.prescricao.lacamentoprescricao;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorDataPrescricaoEConsulta implements IValidarLancamentoPrescricao{
    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(PrescricaoRequestDTO prescricaoRequestDTO) {
        var dataConsulta = consultaRepository.obterDataConsultaPorId(prescricaoRequestDTO.idConsulta());
        if (dataConsulta.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data da prescrição não pode ser anterior à data da consulta");
        }
    }
}
