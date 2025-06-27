package com.uninter.api.sghss.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //csrf -> csrf.disable()
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Login
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll() // libera o login
                        .requestMatchers(HttpMethod.PUT, "/auth/liberaracessomedico/{id}").hasRole("ADMIN")

                        // ConsultaController
                        .requestMatchers(HttpMethod.POST, "/consultas").hasAnyRole("ADMIN", "MEDICO", "USUARIO")
                        .requestMatchers(HttpMethod.GET, "/consultas/{id}").hasAnyRole("ADMIN", "MEDICO", "USUARIO")
                        .requestMatchers(HttpMethod.GET, "/consultas/consultasporpaciente/{id}").hasAnyRole("ADMIN", "MEDICO")
                        .requestMatchers(HttpMethod.DELETE, "/consultas").hasAnyRole("ADMIN", "MEDICO", "USUARIO")

                        // MedicoController
                        .requestMatchers(HttpMethod.POST, "/medicos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/medicos/{id}").hasAnyRole("ADMIN", "MEDICO", "USUARIO")
                        .requestMatchers(HttpMethod.GET, "/medicos").hasAnyRole("ADMIN", "MEDICO", "USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/medicos/{id}").hasAnyRole("ADMIN", "MEDICO")
                        .requestMatchers(HttpMethod.DELETE, "/medicos/{id}").hasRole("ADMIN")

                        // PacienteController
                        //.requestMatchers(HttpMethod.POST, "/pacientes").hasRole("ADMIN") // Ou .permitAll() se for auto-registro
                        .requestMatchers(HttpMethod.GET, "/pacientes/{id}").hasAnyRole("ADMIN", "MEDICO", "USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/pacientes/{id}").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes/{id}").hasRole("ADMIN")

                        // PrescricaoController
                        .requestMatchers(HttpMethod.POST, "/prescricoes").hasAnyRole("ADMIN", "MEDICO")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de segurança personalizado
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
