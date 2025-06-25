package com.uninter.api.sghss.domain.validations.prescricao.lacamentoprescricao;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaAtiva implements IValidarLancamentoPrescricao{
    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(PrescricaoRequestDTO prescricaoRequestDTO) {
        var consultaIsAtiva = consultaRepository.verificaSeConsultaEstaAtiva(prescricaoRequestDTO.idConsulta());

        if (!consultaIsAtiva) {
            throw new IllegalArgumentException("A consulta selecionada não está ativa");
        }
    }
}
