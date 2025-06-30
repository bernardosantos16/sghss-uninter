package com.uninter.api.sghss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uninter.api.sghss.domain.dto.request.PacienteRequestDTO;
import com.uninter.api.sghss.domain.enums.Sexo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @DisplayName("Deveria retornar status 201 ao cadastrar paciente com qualquer role")
    @WithMockUser
    void cadastrarPacienteCenario1() throws Exception {
        var enderecoDTO = new com.uninter.api.sghss.domain.dto.request.EnderecoRequestDTO(
                "Rua Paciente", "Bairro Paciente", "87654321", "Cidade Paciente", "TP", "321", null
        );
        var pacienteDTO = new PacienteRequestDTO(
                "Paciente Teste",
                "12345678902",
                LocalDate.of(1990, 1, 1),
                Sexo.M,
                "(11) 91234-5678",
                "paciente2@teste.com",
                enderecoDTO
        );

        mockMvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteDTO)))
                .andExpect(status().isCreated());
    }
}
