package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;


import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntencedencia implements IValidarAgendamentoConsulta{
    @Override
    public void validar(ConsultaRequestDTO consultaRequestDTO) {
        var dataConsulta = consultaRequestDTO.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataConsulta).toMinutes();

        if (diferenca < 30) {
            throw new UnprocessebleEntityException("A consulta deve ser agendada com pelo menos 30 minutos de antecedência");
        }
    }
}
