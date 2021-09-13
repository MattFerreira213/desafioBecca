package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Utils.Operacao;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OperacaoService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta sacar(Long numeroDaConta, double valorDeSaque) {
        var conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
        var valorSaldo = Optional.of(conta.getSaldo()).orElseThrow().doubleValue();
        var novoValorSaldo = Operacao.sacar(valorDeSaque, valorSaldo);

        conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));

        return contaRepository.save(conta);
    }

    public Conta depositar(Long numeroDaConta, double valorDeDeposito){
        var conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
        var valorSaldo = Optional.of(conta.getSaldo()).orElseThrow().doubleValue();
        var novoValorSaldo = Operacao.depositar(valorDeDeposito, valorSaldo);

        conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));

        return contaRepository.save(conta);
    }

    public Optional<BigDecimal> consultarSaldo(Long numeroDaConta) {
        var conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
        var valorSaldo = Optional.of(conta.getSaldo());
        return valorSaldo;
    }
}
