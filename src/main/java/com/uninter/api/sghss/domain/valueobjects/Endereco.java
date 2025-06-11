package com.uninter.api.sghss.domain.valueobjects;

import com.uninter.api.sghss.domain.dto.request.EnderecoRequestDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoRequestDTO enderecoRequestDTO) {
        this.logradouro = enderecoRequestDTO.logradouro();
        this.bairro = enderecoRequestDTO.bairro();
        this.cep = enderecoRequestDTO.cep();
        this.numero = enderecoRequestDTO.numero();
        this.complemento = enderecoRequestDTO.complemento();
        this.cidade = enderecoRequestDTO.cidade();
        this.uf = enderecoRequestDTO.uf();
    }

    public void atualizar(EnderecoRequestDTO enderecoRequestDTO) {
        if (enderecoRequestDTO.logradouro() != null) {
            this.logradouro = enderecoRequestDTO.logradouro();
        }
        if (enderecoRequestDTO.bairro() != null) {
            this.bairro = enderecoRequestDTO.bairro();
        }
        if (enderecoRequestDTO.cep() != null) {
            this.cep = enderecoRequestDTO.cep();
        }
        if (enderecoRequestDTO.numero() != null) {
            this.numero = enderecoRequestDTO.numero();
        }
        if (enderecoRequestDTO.complemento() != null) {
            this.complemento = enderecoRequestDTO.complemento();
        }
        if (enderecoRequestDTO.cidade() != null) {
            this.cidade = enderecoRequestDTO.cidade();
        }
        if (enderecoRequestDTO.uf() != null) {
            this.uf = enderecoRequestDTO.uf();
        }
    }

}
