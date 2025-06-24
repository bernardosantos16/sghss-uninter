package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.domain.enums.StatusConsulta;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoOcupado implements IValidarAgendamentoConsulta{
    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaRequestDTO consultaRequestDTO) {
        var medicoIsOcupado = consultaRepository.verificaSeMedicoEstaOcupado(
                consultaRequestDTO.idMedico(),
                consultaRequestDTO.data(),
                StatusConsulta.CANCELADA
        );
        if (medicoIsOcupado){
            throw new UnprocessebleEntityException("O médico já está ocupado nesse horário.");
        }
    }
}
