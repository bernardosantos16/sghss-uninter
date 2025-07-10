package com.uninter.api.sghss.domain.validations.consulta.agendarconsulta;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.infra.exceptions.BadRequestException;
import com.uninter.api.sghss.repository.EspecialidadeRepository;
import com.uninter.api.sghss.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEspecialidadeMedico implements IValidarAgendamentoConsulta{
    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadeRepository especialidadeRepository;

    @Override
    public void validar(ConsultaRequestDTO consultaRequestDTO) {
        if (consultaRequestDTO.idEspecialidade() == null) {
            return;
        }
        var especialidade = especialidadeRepository.getReferenceById(consultaRequestDTO.idEspecialidade());
        var medicoTemEspecialidade = medicoRepository
                .existsByIdAndEspecialidade(consultaRequestDTO.idMedico(), especialidade);
        if (!medicoTemEspecialidade) {
            throw new BadRequestException("O médico selecionado não possui a especialidade necessária para esta consulta.");
        }
    }
}
