package com.uninter.api.sghss.domain.entity;

import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestMedicoDTO;
import com.uninter.api.sghss.domain.valueobjects.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = true;

    public Medico(MedicoRequestDTO medicoRequestDTO, Especialidade especialidade){
        this.nome = medicoRequestDTO.nome();
        this.email = medicoRequestDTO.email();
        this.telefone = medicoRequestDTO.telefone();
        this.crm = medicoRequestDTO.crm();
        this.especialidade = especialidade;
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
