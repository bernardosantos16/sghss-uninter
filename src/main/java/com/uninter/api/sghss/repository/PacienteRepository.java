package com.uninter.api.sghss.repository;

import com.uninter.api.sghss.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpf(String cpf);

    @Query("SELECT m.ativo FROM Paciente m WHERE m.id = :id")
    Boolean verificaSePacienteEstaAtivo(Long id);
}
