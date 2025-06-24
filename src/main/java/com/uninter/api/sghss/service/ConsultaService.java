package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.CancelamentoRequestConsultaDTO;
import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.domain.dto.response.ConsultaDetailedResponseDTO;
import com.uninter.api.sghss.domain.entity.Consulta;
import com.uninter.api.sghss.domain.entity.Medico;
import com.uninter.api.sghss.domain.enums.StatusConsulta;
import com.uninter.api.sghss.domain.validations.consulta.agendarconsulta.IValidarAgendamentoConsulta;
import com.uninter.api.sghss.infra.exceptions.BadRequestException;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import com.uninter.api.sghss.mapper.ConsultaMapper;
import com.uninter.api.sghss.repository.ConsultaRepository;
import com.uninter.api.sghss.repository.MedicoRepository;
import com.uninter.api.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<IValidarAgendamentoConsulta> validadoresAgendamentoDeConsulta;

    private Consulta findConsultaById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada com o ID: " + id));
    }

    public ConsultaDetailedResponseDTO cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO) {
        var paciente = pacienteRepository.findById(consultaRequestDTO.idPaciente())
                .orElseThrow(() -> new BadRequestException("Paciente não encontrado com o ID: " + consultaRequestDTO.idPaciente()));

        var medico = escolherMedico(consultaRequestDTO);
        if (medico == null) {
            throw new UnprocessebleEntityException("Não há médicos disponíveis.");
        }

        validadoresAgendamentoDeConsulta.forEach(v -> v.validar(consultaRequestDTO));

        var consulta = new Consulta(medico, paciente, consultaRequestDTO.data(), consultaRequestDTO.observacoes());

        consultaRepository.save(consulta);
        return consultaMapper.consultaToConsultaDetailedResponseDTO(consulta);
    }

    private Medico escolherMedico(ConsultaRequestDTO consultaRequestDTO) {
        if (consultaRequestDTO.idMedico() != null){
             return medicoRepository.findById(consultaRequestDTO.idMedico())
                    .orElseThrow(() -> new BadRequestException("Médico não encontrado com o ID: " + consultaRequestDTO.idMedico()));
        }
        if (consultaRequestDTO.especialidade() == null){
            throw new BadRequestException("Especialidade não informada.");
        }
        return medicoRepository.medicoAleatorioLivreNaData(consultaRequestDTO.especialidade(), consultaRequestDTO.data());

    }

    public ConsultaDetailedResponseDTO getConsultaById(Long id) {
        // Aqui você pode implementar a lógica para buscar uma consulta por ID
        var consulta = findConsultaById(id);
        return consultaMapper.consultaToConsultaDetailedResponseDTO(consulta);
    }

    public Page<ConsultaDetailedResponseDTO> getConsultaPorPaciente(Long id, Pageable pageable) {
        var consultas = consultaRepository.getConsultaPorPaciente(id, pageable);
        return consultas.map(consultaMapper::consultaToConsultaDetailedResponseDTO);
    }

    public void cancelarConsulta(CancelamentoRequestConsultaDTO cancelamentoRequestConsultaDTO) {
        // Implementar a lógica para cancelar uma consulta
        var consulta = findConsultaById(cancelamentoRequestConsultaDTO.idConsulta());
        if (consulta.getStatusConsulta() == StatusConsulta.CANCELADA) {
            throw new BadRequestException("Consulta já cancelada");
        }
        consulta.cancelar(cancelamentoRequestConsultaDTO.motivoCancelamento());

    }
}
