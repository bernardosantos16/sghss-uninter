package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorPacienteAtivo implements IValidarAgendamentoConsulta {
    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public void validar(ConsultaRequestDTO consultaRequestDTO) {
        if (consultaRequestDTO.idPaciente() == null) {
            return; // Se não foi informado o ID do paciente, não há validação a ser feita
        }
        var pacienteIsAtivo = pacienteRepository.verificaSePacienteEstaAtivo(consultaRequestDTO.idPaciente());
        if (!pacienteIsAtivo) {
            throw new IllegalArgumentException("O paciente selecionado não está ativo");
        }
    }
}
