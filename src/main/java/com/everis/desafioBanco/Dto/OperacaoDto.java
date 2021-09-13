package com.everis.desafioBanco.Dto;

import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Utils.Operacao;
import lombok.Data;

@Data
public class OperacaoDto {

    private Operacao operacaoBancaria;

    private Double valor;

    private Conta contaOrigem;

    private Conta contaDestino;
}
