package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.EspecialidadeRequestDTO;
import com.uninter.api.sghss.domain.dto.response.EspecialidadeResponseDTO;
import com.uninter.api.sghss.domain.entity.Especialidade;
import com.uninter.api.sghss.mapper.EspecialidadeMapper;
import com.uninter.api.sghss.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    EspecialidadeMapper especialidadeMapper;

    public EspecialidadeResponseDTO cadastrarEspecialidade(EspecialidadeRequestDTO especialidadeRequestDTO) {
        var especialidade = new Especialidade(especialidadeRequestDTO);
        especialidadeRepository.save(especialidade);
        return especialidadeMapper.especialidadeToEspecialidadeResponseDTO(especialidade);
    }

    public EspecialidadeResponseDTO getEspecialidadeById(Long id) {
        var especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada com o ID: " + id));
        return especialidadeMapper.especialidadeToEspecialidadeResponseDTO(especialidade);
    }
}
