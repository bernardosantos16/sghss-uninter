package com.uninter.api.sghss.domain.entity;

import com.uninter.api.sghss.domain.dto.request.MedicoRequestDTO;
import com.uninter.api.sghss.domain.dto.request.PacienteRequestDTO;
import com.uninter.api.sghss.domain.dto.request.UpdateRequestPacienteDTO;
import com.uninter.api.sghss.domain.enums.Sexo;
import com.uninter.api.sghss.domain.valueobjects.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;

    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private String telefone;
    private String email;

    @Embedded
    private Endereco endereco;

    private boolean ativo = true;

    public Paciente(PacienteRequestDTO pacienteRequestDTO){
        this.nome = pacienteRequestDTO.nome();
        this.cpf = pacienteRequestDTO.cpf();
        this.dataNascimento = pacienteRequestDTO.dataNascimento();
        this.sexo = pacienteRequestDTO.sexo();
        this.email = pacienteRequestDTO.email();
        this.telefone = pacienteRequestDTO.telefone();
        this.endereco = new Endereco(pacienteRequestDTO.endereco());
        // this.ativo = true; // Por padrão, o médico é ativo ao ser criado
    }

    public void atualizar(UpdateRequestPacienteDTO updateRequestPacienteDTO) {
        if (updateRequestPacienteDTO.nome() != null) {
            this.nome = updateRequestPacienteDTO.nome();
        }
        if (updateRequestPacienteDTO.telefone() != null) {
            this.telefone = updateRequestPacienteDTO.telefone();
        }
        if (updateRequestPacienteDTO.email() != null) {
            this.email = updateRequestPacienteDTO.email();
        }
        if (updateRequestPacienteDTO.endereco() != null) {
            this.endereco.atualizar(updateRequestPacienteDTO.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }

}
