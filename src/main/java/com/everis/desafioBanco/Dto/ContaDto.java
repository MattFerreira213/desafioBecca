package com.everis.desafioBanco.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ContaDto {

    @NotBlank
    private String cpf;

    @NotNull
    private Long agencia;

    @NotNull
    private Long numeroDaConta;

    @NotNull
    private Long digitoVerificador;

    @NotNull
    private String tipoDaConta;

    @NotNull
    private BigDecimal saldo;
}
