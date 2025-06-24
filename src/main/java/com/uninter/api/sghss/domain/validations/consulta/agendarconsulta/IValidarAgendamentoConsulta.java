package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;

public interface IValidarAgendamentoConsulta {
    void validar(ConsultaRequestDTO consultaRequestDTO);
}
