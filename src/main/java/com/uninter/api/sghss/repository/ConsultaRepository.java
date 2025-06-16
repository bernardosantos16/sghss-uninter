package com.uninter.api.sghss.repository;

import com.uninter.api.sghss.domain.entity.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("""
            select c from Consulta c where c.paciente.id = :id
            and c.statusConsulta != 'CANCELADA'
            """)
    Page<Consulta> getConsultaPorPaciente(Long id, Pageable pageable);
}
