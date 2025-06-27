package com.uninter.api.sghss.domain.validations.consulta.cancelarconsulta;

import com.uninter.api.sghss.domain.dto.request.CancelamentoRequestConsultaDTO;
import com.uninter.api.sghss.domain.entity.Consulta;
import com.uninter.api.sghss.domain.enums.MotivoCancelamento;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidadorHorarioAntecedenciaTest {

    @InjectMocks
    private ValidadorHorarioAntecedencia validador;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    @DisplayName("Deveria lançar exceção se a consulta não for encontrada")
    void validarCenario1() {
        var cancelamentoDTO = new CancelamentoRequestConsultaDTO(1L, MotivoCancelamento.PACIENTE_CANCELOU);
        when(consultaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> validador.validar(cancelamentoDTO));
    }

    @Test
    @DisplayName("Deveria lançar exceção se o cancelamento for com menos de 6 horas de antecedência")
    void validarCenario2() {
        var dataConsulta = LocalDateTime.now().plusHours(5);
        var consulta = new Consulta(null, null, dataConsulta, null);
        var cancelamentoDTO = new CancelamentoRequestConsultaDTO(1L, MotivoCancelamento.PACIENTE_CANCELOU);

        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        assertThrows(UnprocessebleEntityException.class, () -> validador.validar(cancelamentoDTO));
    }

    @Test
    @DisplayName("Não deveria lançar exceção se o cancelamento for com 6 horas ou mais de antecedência")
    void validarCenario3() {
        var dataConsulta = LocalDateTime.now().plusHours(6).plusMinutes(1);
        var consulta = new Consulta(null, null, dataConsulta, null);
        var cancelamentoDTO = new CancelamentoRequestConsultaDTO(1L, MotivoCancelamento.PACIENTE_CANCELOU);

        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        assertDoesNotThrow(() -> validador.validar(cancelamentoDTO));
    }
}
