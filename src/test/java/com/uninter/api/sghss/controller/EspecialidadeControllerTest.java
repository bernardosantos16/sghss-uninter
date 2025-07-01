package com.uninter.api.sghss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uninter.api.sghss.domain.dto.request.EnderecoRequestDTO;
import com.uninter.api.sghss.domain.dto.request.EspecialidadeRequestDTO;
import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
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
class EspecialidadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @DisplayName("Deveria retornar status 403 ao tentar cadastrar especialidade sem permissão de ADMIN")
    @WithMockUser(roles = "USUARIO")
    void cadastrarMedicoCenario1() throws Exception {
        var especialidadeDTO = new EspecialidadeRequestDTO(
            "Dermatologia"
        );

        mockMvc.perform(post("/especialidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(especialidadeDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    @DisplayName("Deveria retornar status 201 ao cadastrar especialidade com permissão de ADMIN")
    @WithMockUser(roles = "ADMIN")
    void cadastrarMedicoCenario2() throws Exception {
        var especialidadeDTO = new EspecialidadeRequestDTO(
                "Dermatologia"
        );

        mockMvc.perform(post("/especialidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(especialidadeDTO)))
                .andExpect(status().isCreated());
    }
}
