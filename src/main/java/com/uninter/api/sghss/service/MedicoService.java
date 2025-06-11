package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.dto.response.MedicoResponseDTO;
import com.uninter.api.sghss.domain.entity.Medico;
import com.uninter.api.sghss.mapper.MedicoMapper;
import com.uninter.api.sghss.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    MedicoMapper medicoMapper;

    public MedicoResponseDTO cadastrarMedico(MedicoRequestDTO medicoRequestDTO){
        Medico medico = new Medico(medicoRequestDTO);
        medicoRepository.save(medico);
        return medicoMapper.medicoToMedicoResponseDTO(medico);
    }

}
