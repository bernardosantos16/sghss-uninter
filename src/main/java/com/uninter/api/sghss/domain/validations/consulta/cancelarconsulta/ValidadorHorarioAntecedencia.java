package com.uninter.api.sghss.domain.validations.consulta.cancelarconsulta;

import com.uninter.api.sghss.domain.dto.request.CancelamentoRequestConsultaDTO;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements IValidarCancelamentoDeConsultas{
    @Autowired
    ConsultaRepository consultaRepository;
    @Override
    public void validar(CancelamentoRequestConsultaDTO cancelamentoRequestConsultaDTO) {
        var consulta = consultaRepository.findById(cancelamentoRequestConsultaDTO.idConsulta())
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
        var dataConsulta = consulta.getData();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataConsulta).toHours();

        if (diferenca < 6) {
            throw new UnprocessebleEntityException("A consulta deve ser cancelada com pelo menos 6 horas de antecedência");
        }
    }
}
