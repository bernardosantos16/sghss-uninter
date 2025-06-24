package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements IValidarAgendamentoConsulta{
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(ConsultaRequestDTO consultaRequestDTO) {
        var medicoIsAtivo = medicoRepository.verificaSeMedicoEstaAtivo(consultaRequestDTO.idMedico());
        if (!medicoIsAtivo) {
            throw new IllegalArgumentException("O médico selecionado não está ativo.");
        }
    }
}
