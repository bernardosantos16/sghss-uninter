package com.uninter.api.sghss.repository;

import com.uninter.api.sghss.domain.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //todo: mudar para pageable quando tiver mais de 10 médicos cadastrados
    List<Medico> findAllByAtivoTrue();


    @Query("SELECT m.ativo FROM Medico m WHERE m.id = :id")
    Boolean verificaSeMedicoEstaAtivo(Long id);
}
