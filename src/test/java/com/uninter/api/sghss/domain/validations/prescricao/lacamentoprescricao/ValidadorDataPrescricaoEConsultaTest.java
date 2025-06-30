package com.uninter.api.sghss.domain.validations.prescricao.lacamentoprescricao;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidadorDataPrescricaoEConsultaTest {

    @InjectMocks
    private ValidadorDataPrescricaoEConsulta validador;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    @DisplayName("Deveria lançar exceção se a data da prescrição for anterior à data da consulta")
    void validarCenario1() {
        var prescricaoDTO = new PrescricaoRequestDTO(1L, "Medicamento", "Posologia", "Observacoes");
        when(consultaRepository.obterDataConsultaPorId(1L)).thenReturn(LocalDateTime.now().minusDays(1));

        assertThrows(IllegalArgumentException.class, () -> validador.validar(prescricaoDTO));
    }

    @Test
    @DisplayName("Não deveria lançar exceção se a data da prescrição for igual ou posterior à data da consulta")
    void validarCenario2() {
        var prescricaoDTO = new PrescricaoRequestDTO(1L, "Medicamento", "Posologia", "Observacoes");
        when(consultaRepository.obterDataConsultaPorId(1L)).thenReturn(LocalDateTime.now().plusDays(1));

        assertDoesNotThrow(() -> validador.validar(prescricaoDTO));
    }
}
