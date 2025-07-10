package com.uninter.api.sghss.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        var securitySchemeName = "Authentication";
        return new OpenAPI()
                .info(new Info()
                        .title("SGHSS - Sistema de Gestão Hospitalar")
                        .version("v1.0")
                        .description("API REST para gerenciamento de consultas, médicos, pacientes e prescrições")
                        .contact(new Contact()
                                .name("Bernardo dos Santos")
                                .email("bernardo@email.com")
                                .url("https://github.com/bernardosantos16"))
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
