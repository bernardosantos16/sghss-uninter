package com.uninter.api.sghss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.enums.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @DisplayName("Deveria retornar status 403 ao tentar cadastrar médico sem permissão de ADMIN")
    @WithMockUser(roles = "USUARIO")
    void cadastrarMedicoCenario1() throws Exception {
        var medicoDTO = new MedicoRequestDTO(
                "Medico Teste",
                "medico@teste.com",
                "(99) 99999-9999",
                "123456",
                Especialidade.CARDIOLOGIA,
                null // Endereço não é relevante para este teste de autorização
        );

        mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicoDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    @DisplayName("Deveria retornar status 201 ao cadastrar médico com permissão de ADMIN")
    @WithMockUser(roles = "ADMIN")
    void cadastrarMedicoCenario2() throws Exception {
        var enderecoDTO = new com.uninter.api.sghss.domain.dto.request.EnderecoRequestDTO(
                "Rua Teste", "Bairro Teste", "12345678", "Cidade Teste", "TS", "123", "Apto 1"
        );
        var medicoDTO = new MedicoRequestDTO(
                "Medico Admin",
                "medico.admin2@teste.com",
                "(11) 98888-7778",
                "654322",
                Especialidade.DERMATOLOGIA,
                enderecoDTO
        );

        mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicoDTO)))
                .andExpect(status().isCreated());
    }
}
