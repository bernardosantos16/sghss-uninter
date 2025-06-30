package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.domain.enums.StatusConsulta;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidadorMedicoOcupadoTest {

    @InjectMocks
    private ValidadorMedicoOcupado validador;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    @DisplayName("Deveria lançar exceção se o médico estiver ocupado")
    void validarCenario1() {
        var data = LocalDateTime.now();
        var consultaDTO = new ConsultaRequestDTO(1L, 1L, data, null, null);
        when(consultaRepository.verificaSeMedicoEstaOcupado(1L, data, StatusConsulta.CANCELADA)).thenReturn(true);

        assertThrows(UnprocessebleEntityException.class, () -> validador.validar(consultaDTO));
    }

    @Test
    @DisplayName("Não deveria lançar exceção se o médico não estiver ocupado")
    void validarCenario2() {
        var data = LocalDateTime.now();
        var consultaDTO = new ConsultaRequestDTO(1L, 1L, data, null, null);
        when(consultaRepository.verificaSeMedicoEstaOcupado(1L, data, StatusConsulta.CANCELADA)).thenReturn(false);

        assertDoesNotThrow(() -> validador.validar(consultaDTO));
    }
}
