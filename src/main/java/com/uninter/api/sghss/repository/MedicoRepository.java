package com.uninter.api.sghss.repository;

import com.uninter.api.sghss.domain.entity.Especialidade;
import com.uninter.api.sghss.domain.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //todo: mudar para pageable quando tiver mais de 10 médicos cadastrados
    List<Medico> findAllByAtivoTrue();

   Boolean existsByIdAndEspecialidade(Long id, Especialidade especialidade);

    @Query("SELECT m.ativo FROM Medico m WHERE m.id = :id")
    Boolean verificaSeMedicoEstaAtivo(Long id);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.ativo = true
            AND
            m.especialidade = :especialidade
            AND
            m.id NOT IN (
                SELECT c.medico.id FROM Consulta c
                WHERE c.data = :data
                AND c.statusConsulta = 'PENDENTE'
            )
            ORDER BY RANDOM()
            LIMIT 1
            """)
    Medico medicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
