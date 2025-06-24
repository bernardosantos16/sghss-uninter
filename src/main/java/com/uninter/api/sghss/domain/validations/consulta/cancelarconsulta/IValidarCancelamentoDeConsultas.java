package com.uninter.api.sghss.domain.validations.consulta.cancelarconsulta;

import com.uninter.api.sghss.domain.dto.request.CancelamentoRequestConsultaDTO;

public interface IValidarCancelamentoDeConsultas {

    void validar(CancelamentoRequestConsultaDTO cancelamentoRequestConsultaDTO);
}
