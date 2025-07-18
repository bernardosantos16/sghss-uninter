package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestMedicoDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoDetailedResponseDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoResponseDTO;
import com.uninter.api.sghss.domain.entity.Medico;
import com.uninter.api.sghss.domain.entity.Paciente;
import com.uninter.api.sghss.infra.exceptions.BadRequestException;
import com.uninter.api.sghss.infra.exceptions.NotFoundException;
import com.uninter.api.sghss.mapper.MedicoMapper;
import com.uninter.api.sghss.repository.EspecialidadeRepository;
import com.uninter.api.sghss.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    EspecialidadeRepository especialidadeRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    private Medico findMedicoById(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medico não encontrado com o ID:" + id));
    }

    public MedicoDetailedResponseDTO cadastrarMedico(MedicoRequestDTO medicoRequestDTO) {
        var especialidade = especialidadeRepository.findById(medicoRequestDTO.idEspecialidade())
                .orElseThrow(() -> new BadRequestException("Especialidade não existente com o ID: " + medicoRequestDTO.idEspecialidade()));
        Medico medico = new Medico(medicoRequestDTO, especialidade);
        medicoRepository.save(medico);
        return medicoMapper.medicoToMedicoDetailedResponseDTO(medico);
    }

    public MedicoDetailedResponseDTO getMedicoById(Long id) {
        Medico medico = findMedicoById(id);
        return medicoMapper.medicoToMedicoDetailedResponseDTO(medico);
    }

    public List<MedicoResponseDTO> getListMedicos() {
        var medicos = medicoRepository.findAllByAtivoTrue();
        return medicos.stream()
                .map(medicoMapper::medicoToMedicoResponseDTO)
                .toList();
    }

    public MedicoDetailedResponseDTO updateMedico(Long id, UpdateRequestMedicoDTO updateRequestMedicoDTO) {
        Medico medico = findMedicoById(id);
        medico.atualizar(updateRequestMedicoDTO);
        return medicoMapper.medicoToMedicoDetailedResponseDTO(medico);
    }

    // exclusao lógica - inativação do médico
    public void inativarMedico(Long id) {
        var medico = findMedicoById(id);
        medico.inativar();
    }

}
