package com.uninter.api.sghss.domain.entity;

import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestMedicoDTO;
import com.uninter.api.sghss.domain.enums.Especialidade;
import com.uninter.api.sghss.domain.valueobjects.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = true;

    public Medico(MedicoRequestDTO medicoRequestDTO){
        this.nome = medicoRequestDTO.nome();
        this.email = medicoRequestDTO.email();
        this.telefone = medicoRequestDTO.telefone();
        this.crm = medicoRequestDTO.crm();
        this.especialidade = medicoRequestDTO.especialidade();
        this.endereco = new Endereco(medicoRequestDTO.endereco());
        // this.ativo = true;
    }

    public void atualizar(UpdateRequestMedicoDTO updateRequestMedicoDTO) {
        if (updateRequestMedicoDTO.nome() != null) {
            this.nome = updateRequestMedicoDTO.nome();
        }
        if (updateRequestMedicoDTO.telefone() != null) {
            this.telefone = updateRequestMedicoDTO.telefone();
        }
        if (updateRequestMedicoDTO.endereco() != null) {
            this.endereco.atualizar(updateRequestMedicoDTO.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }

}
