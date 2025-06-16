package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.PacienteRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestPacienteDTO;
import com.uninter.api.sghss.domain.dto.response.PacienteDetailedResponseDTO;
import com.uninter.api.sghss.domain.entity.Paciente;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.mapper.PacienteMapper;
import com.uninter.api.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    PacienteMapper pacienteMapper;

    private final String NOT_FOUND_MESSAGE = "Paciente não encontrado com o ID: ";

    public Paciente findPacienteById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE + id));
    }

    public PacienteDetailedResponseDTO cadastrarPaciente(PacienteRequestDTO pacienteRequestDTO){
        Paciente paciente = new Paciente(pacienteRequestDTO);
        pacienteRepository.save(paciente);
        return pacienteMapper.pacienteToPacienteDetailedResponseDTO(paciente);
    }

    public PacienteDetailedResponseDTO getPacienteById(Long id) {
        Paciente paciente = findPacienteById(id);
        return pacienteMapper.pacienteToPacienteDetailedResponseDTO(paciente);
    }

    public PacienteDetailedResponseDTO atualizarPaciente(Long id, UpdateRequestPacienteDTO updateRequestPacienteDTO) {
        Paciente paciente = findPacienteById(id);
        paciente.atualizar(updateRequestPacienteDTO);
        return pacienteMapper.pacienteToPacienteDetailedResponseDTO(paciente);
    }

    public void inativarPaciente(Long id) {
        var paciente = findPacienteById(id);
        paciente.inativar();
    }
}
