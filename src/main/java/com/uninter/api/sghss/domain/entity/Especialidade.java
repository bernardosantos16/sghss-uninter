package com.uninter.api.sghss.domain.entity;

import com.uninter.api.sghss.domain.dto.request.EspecialidadeRequestDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "especialidades")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String especialidade;

    @OneToMany(mappedBy = "especialidade")
    private List<Medico> medicos;

    public Especialidade(EspecialidadeRequestDTO especialidadeRequestDTO){
        this.especialidade = especialidadeRequestDTO.especialidade();
    }
}
