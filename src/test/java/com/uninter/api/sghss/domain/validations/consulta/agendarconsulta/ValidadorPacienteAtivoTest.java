package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.repository.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidadorPacienteAtivoTest {

    @InjectMocks
    private ValidadorPacienteAtivo validador;

    @Mock
    private PacienteRepository pacienteRepository;

    @Test
    @DisplayName("Deveria lançar exceção se o paciente não estiver ativo")
    void validarCenario1() {
        var consultaDTO = new ConsultaRequestDTO(1L, 1L, null, null, null);
        when(pacienteRepository.verificaSePacienteEstaAtivo(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> validador.validar(consultaDTO));
    }

    @Test
    @DisplayName("Não deveria lançar exceção se o paciente estiver ativo")
    void validarCenario2() {
        var consultaDTO = new ConsultaRequestDTO(1L, 1L, null, null, null);
        when(pacienteRepository.verificaSePacienteEstaAtivo(1L)).thenReturn(true);

        assertDoesNotThrow(() -> validador.validar(consultaDTO));
    }
}
