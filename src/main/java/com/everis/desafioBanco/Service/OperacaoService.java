package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Utils.Operacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OperacaoService {

    @Autowired
    private ContaRepository contaRepository;

    public String sacar(Long numeroDaConta, double valorDeSaque) {

        var conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
        var valorSaldo = conta.getSaldo().doubleValue();

        if (valorDeSaque < valorSaldo) {
            var novoValorSaldo = Operacao.sacar(valorDeSaque, valorSaldo);
            conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
            contaRepository.save(conta);
            return "Saque realizado com sucesso!";
        } else {
            return "Saque não autorizado, saldo insuficiente!";
        }

    }

    public String depositar(Long numeroDaConta, double valorDeDeposito) {

        var conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
        var valorSaldo = Optional.of(conta.getSaldo()).orElseThrow().doubleValue();
        if (valorDeDeposito > 0) {
            var novoSaldo = Operacao.depositar(valorDeDeposito, valorSaldo);

            conta.setSaldo(BigDecimal.valueOf(novoSaldo));

            contaRepository.save(conta);

            return "Deposito realizado com sucesso!";
        } else {
            return "Deposito não realizado!";
        }

    }

    public BigDecimal consultarSaldo(Long numeroDaConta) {
        var conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
        var valorSaldo = conta.getSaldo();
        return valorSaldo;
    }

    public String tranferir(Long numeroDaContaO, Long numeroDaContaD, double valorDeTransferencia) {

        var contaOrigem = contaRepository.findContaByNumeroDaConta(numeroDaContaO);
        var valorSaldoConatOrigem = Optional.of(contaOrigem.getSaldo()).orElseThrow().doubleValue();

        if (valorDeTransferencia < valorSaldoConatOrigem){

            var contaDestino = contaRepository.findContaByNumeroDaConta(numeroDaContaD);
            var valorSaldoConatDestino = Optional.of(contaDestino.getSaldo()).orElseThrow().doubleValue();

            var novoSaldoContaOrigem = valorSaldoConatOrigem -= valorDeTransferencia;
            var novoSaldoContaDestino = valorSaldoConatDestino += valorDeTransferencia;

            contaOrigem.setSaldo(BigDecimal.valueOf(novoSaldoContaOrigem));
            contaDestino.setSaldo(BigDecimal.valueOf(novoSaldoContaDestino));

            contaRepository.save(contaOrigem);
            contaRepository.save(contaDestino);

            return "Transferencia realizada com sucesso!";
        } else {
            return "Transferencia não autorizada!";
        }
    }


}

