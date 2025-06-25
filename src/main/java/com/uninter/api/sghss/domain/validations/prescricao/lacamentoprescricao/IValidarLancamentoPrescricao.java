package com.uninter.api.sghss.domain.validations.prescricao.lacamentoprescricao;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;

public interface IValidarLancamentoPrescricao {
    void validar(PrescricaoRequestDTO prescricaoRequestDTO);
}
