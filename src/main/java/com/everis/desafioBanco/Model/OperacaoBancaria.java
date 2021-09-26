package com.everis.desafioBanco.Model;

import com.everis.desafioBanco.Enum.EOperacao;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OperacaoBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long numeroDaConta;

    private Long numeroDaContaDestino;

    private BigDecimal valorDeTransacao;

    private BigDecimal taxa;

    private EOperacao tipoOperacao;
}
