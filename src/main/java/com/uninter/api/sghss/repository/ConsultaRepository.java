package com.uninter.api.sghss.repository;

import com.uninter.api.sghss.domain.entity.Consulta;
import com.uninter.api.sghss.domain.enums.StatusConsulta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("""
            select c from Consulta c where c.paciente.id = :id
            and c.statusConsulta != 'CANCELADA'
            """)
    Page<Consulta> getConsultaPorPaciente(Long id, Pageable pageable);

    @Query("""
            SELECT COUNT(c) > 0 FROM Consulta c
            WHERE c.medico.id = :medicoId
            AND c.data = :data
            AND c.statusConsulta != :status
            """)
    Boolean verificaSeMedicoEstaOcupado(Long medicoId, LocalDateTime data, StatusConsulta status);

    @Query("""
            SELECT COUNT(c) > 0 FROM Consulta c
            WHERE c.id = :id
            AND c.statusConsulta != 'CANCELADA'
            AND c.statusConsulta != 'FINALIZADA'
            """)
    Boolean verificaSeConsultaEstaAtiva(Long id);

//    @Query("""
//            select c.statusConsulta from Consulta c
//            where c.id = :id
//            """)
//    StatusConsulta obterStatusConsultaPorId(Long id);

    @Query("""
            SELECT c.data FROM Consulta c
            WHERE c.id = :id
            """)
    LocalDateTime obterDataConsultaPorId(Long id);
}
