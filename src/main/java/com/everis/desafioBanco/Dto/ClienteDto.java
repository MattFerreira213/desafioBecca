package com.everis.desafioBanco.Dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Data
public class ClienteDto {

    @NotBlank(message = "Campo nome deve ser informado.")
    private String nome;

    @CPF(message = "Formato de CPF inválido.")
    private String cpf;

    @NotBlank(message = "Campo telefone deve ser informado.")
    private String telefone;

    @NotBlank(message = "Campo endereço deve ser informado.")
    private String endereco;






}
