package com.uninter.api.sghss.service;

import com.uninter.api.sghss.domain.dto.request.ConsultaRequestDTO;
import com.uninter.api.sghss.domain.entity.Especialidade;
import com.uninter.api.sghss.domain.entity.Medico;
import com.uninter.api.sghss.domain.entity.Paciente;
import com.uninter.api.sghss.domain.validations.consulta.agendarconsulta.IValidarAgendamentoConsulta;
import com.uninter.api.sghss.infra.exceptions.UnprocessebleEntityException;
import com.uninter.api.sghss.mapper.ConsultaMapper;
import com.uninter.api.sghss.repository.ConsultaRepository;
import com.uninter.api.sghss.repository.EspecialidadeRepository;
import com.uninter.api.sghss.repository.MedicoRepository;
import com.uninter.api.sghss.repository.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private EspecialidadeRepository especialidadeRepository;

    @Mock
    private ConsultaMapper consultaMapper;

    @Mock
    private List<IValidarAgendamentoConsulta> validadores;

    @Test
    @DisplayName("Deveria agendar consulta com sucesso quando um médico é especificado")
    void agendarConsultaComMedicoEspecificado() {
        Long idMedico = 1L;
        Long idPaciente = 1L;
        LocalDateTime data = LocalDateTime.now().plusDays(1);
        var consultaDTO = new ConsultaRequestDTO(idMedico, idPaciente, data, 1L, "Observação");


        when(pacienteRepository.findById(idPaciente)).thenReturn(Optional.of(new Paciente()));
        when(medicoRepository.findById(idMedico)).thenReturn(Optional.of(new Medico()));

        consultaService.cadastrarConsulta(consultaDTO);

        verify(validadores, times(1)).forEach(any());
        verify(consultaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deveria agendar consulta com sucesso escolhendo médico aleatório")
    void agendarConsultaComMedicoAleatorio() {
        Long idPaciente = 1L;
        LocalDateTime data = LocalDateTime.now().plusDays(1);
        var consultaDTO = new ConsultaRequestDTO(null, idPaciente, data, 1L, "Observação");

        when(pacienteRepository.findById(idPaciente)).thenReturn(Optional.of(new Paciente()));
        when(medicoRepository.medicoAleatorioLivreNaData(any(), any())).thenReturn(new Medico());
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(new Especialidade()));


        consultaService.cadastrarConsulta(consultaDTO);

        verify(validadores, times(1)).forEach(any());
        verify(consultaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deveria lançar exceção quando não há médico disponível")
    void agendarConsultaSemMedicoDisponivel() {
        Long idPaciente = 1L;
        LocalDateTime data = LocalDateTime.now().plusDays(1);
        var consultaDTO = new ConsultaRequestDTO(null, idPaciente, data, 1L, "Observação");

        when(pacienteRepository.findById(idPaciente)).thenReturn(Optional.of(new Paciente()));
        when(medicoRepository.medicoAleatorioLivreNaData(any(), any())).thenReturn(null);
        when(especialidadeRepository.findById(1L)).thenReturn(Optional.of(new Especialidade()));


        assertThrows(UnprocessebleEntityException.class, () -> {
            consultaService.cadastrarConsulta(consultaDTO);
        });
    }
}
