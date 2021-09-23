package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Enum.EOperacao;
import com.everis.desafioBanco.Utils.Exceptions.CpfNaoEncontradoException;
import com.everis.desafioBanco.Utils.Exceptions.TransacaoNaoAutorizadaException;
import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Model.OperacaoBancaria;
import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Repository.OperacaoBancariaRepository;
import com.everis.desafioBanco.Utils.Exceptions.ContaNaoEncontradaException;
import com.everis.desafioBanco.Utils.Exceptions.SaldoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OperacaoService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private OperacaoBancariaRepository operacaoBancariaRepository;

    private Optional<Conta> verificarConta;

    public void sacar(OperacaoBancaria dadosOperacaoBancaria) {

        dadosOperacaoBancaria.setNumeroDaConta(dadosOperacaoBancaria.getNumeroDaConta());
        dadosOperacaoBancaria.setNumeroDaContaDestino(dadosOperacaoBancaria.getNumeroDaConta());
        dadosOperacaoBancaria.setValor(dadosOperacaoBancaria.getValor());
        dadosOperacaoBancaria.setTipoOperacao(EOperacao.SAQUE);
        dadosOperacaoBancaria.setTaxa(0);

        var conta = contaRepository.findContaByNumeroDaConta(dadosOperacaoBancaria.getNumeroDaConta());

        verificarConta = Optional.ofNullable(conta);

        if (verificarConta.isPresent()) {

            var valorDeSaque = dadosOperacaoBancaria.getValor().doubleValue();
            var valorSaldo = conta.getSaldo().doubleValue();
            var tipoConta = conta.getTipoDaConta();


            if (valorDeSaque + 10 <= valorSaldo && valorDeSaque > 0) {
                if (tipoConta.equals("Pessoa Fisica") || tipoConta.equals("Pessoa Juridica")){
                    var quantidadeSaque = conta.getQuantidadeDeSaqueSemTaxa();
                    if(quantidadeSaque > 0){
                        var novoValorSaldo = valorSaldo - valorDeSaque ;
                        conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));

                        quantidadeSaque--;

                        conta.setQuantidadeDeSaqueSemTaxa(quantidadeSaque);
                    } else {
                        var taxa = 10;
                        var novoValorSaldo = valorSaldo - (valorDeSaque + taxa);
                        conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
                        dadosOperacaoBancaria.setTaxa(taxa);
                    }
                } else if (tipoConta.equals("Governamental")){
                    var quantidadeSaque = conta.getQuantidadeDeSaqueSemTaxa();
                    if(quantidadeSaque > 0){
                        var novoValorSaldo = valorSaldo - valorDeSaque ;
                        conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));

                        quantidadeSaque--;

                        conta.setQuantidadeDeSaqueSemTaxa(quantidadeSaque);
                    } else {
                        var taxa = 20;
                        var novoValorSaldo = valorSaldo - (valorDeSaque + taxa);
                        conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
                        dadosOperacaoBancaria.setTaxa(taxa);
                    }
                }
                contaRepository.save(conta);
                operacaoBancariaRepository.save(dadosOperacaoBancaria);
            } else {
                throw new SaldoInsuficienteException("Transação não autorizado, saldo insuficiente!");
            }
        } else {
            throw new ContaNaoEncontradaException("Conta não encontrada, verifique o numero da conta");
        }
    }

    public void depositar(OperacaoBancaria dadosOperacaoBancaria) {

        dadosOperacaoBancaria.setNumeroDaConta(dadosOperacaoBancaria.getNumeroDaConta());
        dadosOperacaoBancaria.setNumeroDaContaDestino(dadosOperacaoBancaria.getNumeroDaConta());
        dadosOperacaoBancaria.setValor(dadosOperacaoBancaria.getValor());
        dadosOperacaoBancaria.setTipoOperacao(EOperacao.DEPOSITO);
        dadosOperacaoBancaria.setTaxa(0);

        var conta = contaRepository.findContaByNumeroDaConta(dadosOperacaoBancaria.getNumeroDaConta());

        verificarConta = Optional.ofNullable(conta);
        if (verificarConta.isPresent()) {
            var valorSaldo = conta.getSaldo().doubleValue();
            var valorDeDeposito = dadosOperacaoBancaria.getValor().doubleValue();

            if (valorDeDeposito > 0) {
                var novoValorSaldo = valorDeDeposito + valorSaldo;
                conta.setSaldo(BigDecimal.valueOf(novoValorSaldo));
                contaRepository.save(conta);
                operacaoBancariaRepository.save(dadosOperacaoBancaria);
            } else {
                throw new TransacaoNaoAutorizadaException("Transação não autorizada, valor de peposito inválido!");
            }
        } else {
            throw new ContaNaoEncontradaException("Conta não encontrada, verifique o numero da conta");
        }

    }

    public BigDecimal consultarSaldo(Long numeroDaConta) {
        var conta = contaRepository.findContaByNumeroDaConta(numeroDaConta);
        verificarConta = Optional.ofNullable(conta);
        if (verificarConta.isPresent()){
            var valorSaldo = conta.getSaldo();
            return valorSaldo;
        } else {
            throw new ContaNaoEncontradaException("Conta não encontrada");
        }
    }

    public void tranferir(OperacaoBancaria dadosOperacaoBancaria) {

        dadosOperacaoBancaria.setNumeroDaConta(dadosOperacaoBancaria.getNumeroDaConta());
        dadosOperacaoBancaria.setNumeroDaContaDestino(dadosOperacaoBancaria.getNumeroDaContaDestino());
        dadosOperacaoBancaria.setValor(dadosOperacaoBancaria.getValor());
        dadosOperacaoBancaria.setTipoOperacao(EOperacao.TRANFERENCIA);
        dadosOperacaoBancaria.setTaxa(0);

        var contaOrigem = contaRepository.findContaByNumeroDaConta(dadosOperacaoBancaria.getNumeroDaConta());
        var contaDestino = contaRepository.findContaByNumeroDaConta(dadosOperacaoBancaria.getNumeroDaContaDestino());

        Optional<Conta> verificarContaOrigem = Optional.ofNullable(contaOrigem);
        Optional<Conta> verificarContaDestino = Optional.ofNullable(contaDestino);

        if (verificarContaOrigem.isPresent() && verificarContaDestino.isPresent()) {

            if (contaOrigem != contaDestino) {

                var valorSaldoConatOrigem = contaOrigem.getSaldo().doubleValue();
                var valorSaldoConatDestino = contaDestino.getSaldo().doubleValue();
                var valorDeTransferencia = dadosOperacaoBancaria.getValor().doubleValue();

                if (valorDeTransferencia < valorSaldoConatOrigem && valorDeTransferencia > 0) {
                    var novoSaldoContaOrigem = valorSaldoConatOrigem -= valorDeTransferencia;
                    var novoSaldoContaDestino = valorSaldoConatDestino += valorDeTransferencia;

                    contaOrigem.setSaldo(BigDecimal.valueOf(novoSaldoContaOrigem));
                    contaDestino.setSaldo(BigDecimal.valueOf(novoSaldoContaDestino));

                    contaRepository.save(contaOrigem);
                    contaRepository.save(contaDestino);
                    operacaoBancariaRepository.save(dadosOperacaoBancaria);

                } else {
                    throw new SaldoInsuficienteException("Saldo insuficiente para realizar operação");
                }
            } else {
                throw new TransacaoNaoAutorizadaException("Transação não autorizado verifique o número das contas");
            }
        } else {
            throw new ContaNaoEncontradaException("Conta não encontrada verifique o número das contas");

        }
    }

    public List<OperacaoBancaria> extrato(Long numeroDaConta){
        var operacoes = operacaoBancariaRepository.findExtrartoByNumeroDaConta(numeroDaConta);

        if(operacoes.size() > 0){
            for (OperacaoBancaria ob : operacoes) {
                ob.getNumeroDaConta();
            }
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Conta não encontrada, verifique o numero da conta"));
        }
        return operacoes;
    }
}

