package com.uninter.api.sghss.domain.validations.prescricao.lacamentoprescricao;

import com.uninter.api.sghss.domain.dto.request.PrescricaoRequestDTO;
import com.uninter.api.sghss.repository.ConsultaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidadorConsultaAtivaTest {

    @InjectMocks
    private ValidadorConsultaAtiva validador;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    @DisplayName("Deveria lançar exceção se a consulta não estiver ativa")
    void validarCenario1() {
        var prescricaoDTO = new PrescricaoRequestDTO(1L, "Medicamento", "Posologia", "Observacoes");
        when(consultaRepository.verificaSeConsultaEstaAtiva(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> validador.validar(prescricaoDTO));
    }

    @Test
    @DisplayName("Não deveria lançar exceção se a consulta estiver ativa")
    void validarCenario2() {
        var prescricaoDTO = new PrescricaoRequestDTO(1L, "Medicamento", "Posologia", "Observacoes");
        when(consultaRepository.verificaSeConsultaEstaAtiva(1L)).thenReturn(true);

        assertDoesNotThrow(() -> validador.validar(prescricaoDTO));
    }
}
