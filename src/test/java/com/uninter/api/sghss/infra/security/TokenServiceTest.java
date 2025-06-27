package com.uninter.api.sghss.infra.security;

import com.uninter.api.sghss.domain.entity.Usuario;
import com.uninter.api.sghss.domain.enums.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    @DisplayName("Deveria gerar um token JWT válido")
    void gerarTokenCenario1() {
        Usuario usuario = new Usuario("teste", "123", UserRole.USUARIO);
        String token = tokenService.gerarToken(usuario);
        assertNotNull(token);

        String subject = tokenService.getSubject(token);
        assertEquals("teste", subject);
    }

    @Test
    @DisplayName("Deveria lançar exceção para token inválido ou expirado")
    void getSubjectCenario1() {
        String tokenInvalido = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgU0dIU1MiLCJzdWIiOiJ0ZXN0ZSIsImlhdCI6MTcxODIwOTc0MywiZXhwIjoxNzE4MjA5NzQzfQ.INVALID_SIGNATURE";

        assertThrows(RuntimeException.class, () -> {
            tokenService.getSubject(tokenInvalido);
        });
    }

    @Test
    @DisplayName("Deveria gerar um token e validá-lo com sucesso")
    void gerarEValidarToken() {
        Usuario usuario = new Usuario("validuser", "senha123", UserRole.MEDICO);
        String token = tokenService.gerarToken(usuario);

        assertDoesNotThrow(() -> {
            String subject = tokenService.getSubject(token);
            assertEquals("validuser", subject);
        });
    }
}
