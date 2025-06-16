package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.CancelamentoConsultaDTO;
import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.domain.dto.response.ConsultaDetailedResponseDTO;
import com.uninter.api.sghss.domain.entity.Consulta;
import com.uninter.api.sghss.domain.enums.MotivoCancelamento;
import com.uninter.api.sghss.domain.enums.StatusConsulta;
import com.uninter.api.sghss.infra.exceptions.BadRequestException;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.mapper.ConsultaMapper;
import com.uninter.api.sghss.repository.ConsultaRepository;
import com.uninter.api.sghss.repository.MedicoRepository;
import com.uninter.api.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    private Consulta findConsultaById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada com o ID: " + id));
    }

    public ConsultaDetailedResponseDTO cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO) {
        // Aqui você pode implementar a lógica para cadastrar uma consulta
        // Por exemplo, buscar a consulta pelo ID e mapear para o DTO de resposta
        var paciente = pacienteRepository.getReferenceById(consultaRequestDTO.idPaciente());
        var medico = medicoRepository.getReferenceById(consultaRequestDTO.idMedico());


        //validadoresAgendamentoDeConsulta.forEach(v -> v.validar(consultaDTO));
        // var medico = escolherMedico(consultaDTO);
//        if (medico == null) {
//            throw new ValidacaoException("Não há médicos disponíveis.");
//        }
//        var paciente = pacienteRepository.getReferenceById(consultaDTO.idPaciente());
        var consulta = new Consulta(medico, paciente, consultaRequestDTO.data(), consultaRequestDTO.observacoes());

        consultaRepository.save(consulta);
        return consultaMapper.consultaToConsultaDetailedResponseDTO(consulta);
    }

    public ConsultaDetailedResponseDTO getConsultaById(Long id) {
        // Aqui você pode implementar a lógica para buscar uma consulta por ID
        var consulta = findConsultaById(id);
        return consultaMapper.consultaToConsultaDetailedResponseDTO(consulta);
    }

    public Page<ConsultaDetailedResponseDTO> getConsultaPorPaciente(Long id, Pageable pageable){
        var consultas = consultaRepository.getConsultaPorPaciente(id, pageable);
        return consultas.map(consultaMapper::consultaToConsultaDetailedResponseDTO);
    }

    public void cancelarConsulta(CancelamentoConsultaDTO cancelamentoConsultaDTO) {
        // Implementar a lógica para cancelar uma consulta
        var consulta = findConsultaById(cancelamentoConsultaDTO.idConsulta());
        if (consulta.getStatusConsulta() == StatusConsulta.CANCELADA){
            throw new BadRequestException("Consulta já cancelada");
        }
        consulta.cancelar(cancelamentoConsultaDTO.motivoCancelamento());

    }
}
