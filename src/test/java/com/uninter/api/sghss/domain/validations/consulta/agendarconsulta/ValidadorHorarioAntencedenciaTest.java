package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorHorarioAntencedenciaTest {

    private ValidadorHorarioAntencedencia validador = new ValidadorHorarioAntencedencia();

    @Test
    @DisplayName("Deveria lançar exceção ao tentar agendar consulta com menos de 30 minutos de antecedência")
    void validarCenario1() {
        var consultaDTO = new ConsultaRequestDTO(1L, 1L, LocalDateTime.now().plusMinutes(29), null, null);
        assertThrows(UnprocessebleEntityException.class, () -> validador.validar(consultaDTO));
    }

    @Test
    @DisplayName("Não deveria lançar exceção ao tentar agendar consulta com 31 minutos ou mais de antecedência")
    void validarCenario2() {
        var consultaDTO = new ConsultaRequestDTO(1L, 1L, LocalDateTime.now().plusMinutes(31), null, null);
        assertDoesNotThrow(() -> validador.validar(consultaDTO));
    }
}
