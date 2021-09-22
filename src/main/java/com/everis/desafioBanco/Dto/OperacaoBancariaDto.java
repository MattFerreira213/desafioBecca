package com.everis.desafioBanco.Dto;

import com.everis.desafioBanco.Enum.EOperacao;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OperacaoBancariaDto {

    @NotNull
    private Long numeroDaConta;

    @NotNull
    private Long numeroDaContaDestino;

    @NotNull
    private BigDecimal valor;

    private int taxa;

    private EOperacao tipoOperacao;
}
