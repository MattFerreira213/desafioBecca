package com.everis.desafioBanco.Dto;

import com.everis.desafioBanco.Enum.ETipoDeConta;
import lombok.Data;

import javax.persistence.Column;
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
    @Column(name = "numero_da_conte")
    private Long numeroDaConta;

    @NotNull
    @Column(name = "digito_verificador")
    private Long digitoVerificador;

    @NotNull
    @Column(name = "tipo_da_conta")
    private String tipoDaConta;

    @NotNull
    private BigDecimal saldo;

    private int quantidadeDeSaqueSemTaxa;}
