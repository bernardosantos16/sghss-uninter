package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestMedicoDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoDetailedResponseDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoResponseDTO;
import com.uninter.api.sghss.domain.entity.Medico;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.mapper.MedicoMapper;
import com.uninter.api.sghss.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    MedicoMapper medicoMapper;

    public MedicoDetailedResponseDTO cadastrarMedico(MedicoRequestDTO medicoRequestDTO){
        Medico medico = new Medico(medicoRequestDTO);
        medicoRepository.save(medico);
        return medicoMapper.medicoToMedicoDetailedResponseDTO(medico);
    }

    public MedicoDetailedResponseDTO getMedicoById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com o ID: " + id));
        return medicoMapper.medicoToMedicoDetailedResponseDTO(medico);
    }

    public List<MedicoResponseDTO> getListMedicos() {
        var medicos = medicoRepository.findAllByAtivoTrue();
        return medicos.stream()
                .map(medicoMapper::medicoToMedicoResponseDTO)
                .toList();
    }

    public MedicoDetailedResponseDTO updateMedico(Long id, UpdateRequestMedicoDTO updateRequestMedicoDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com o ID: " + id));
        medico.atualizar(updateRequestMedicoDTO);
        return medicoMapper.medicoToMedicoDetailedResponseDTO(medico);
    }

    // exclusao lógica - inativação do médico
    public void inativarMedico(Long id) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com o ID: " + id));
        medico.inativar();
    }



}
