package com.everis.desafioBanco.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClienteDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String telefone;

    @NotBlank
    private String endereco;

}
