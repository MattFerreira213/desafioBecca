package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Repository.ClienteRepository;
import com.everis.desafioBanco.Repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public void cadastrarConta(Conta conta) {
        Conta dadosConta = new Conta();

        dadosConta.setAgencia(conta.getAgencia());
        dadosConta.setCpf(conta.getCpf());
        dadosConta.setNumeroDaConta(conta.getNumeroDaConta());
        dadosConta.setDigitoVerificador(conta.getDigitoVerificador());
        dadosConta.setTipoDaConta(conta.getTipoDaConta());
        dadosConta.setSaldo(conta.getSaldo());

        contaRepository.save(dadosConta);

    }

    public Conta listarConta(String cpf) {
        var conta = contaRepository.findContaByCpf(cpf);
        conta.getAgencia();
        conta.getCpf();
        conta.getNumeroDaConta();
        conta.getDigitoVerificador();
        conta.getTipoDaConta();
        conta.getSaldo();

        return conta;
    }

    public void deletarConta(String cpf) {
        var conta = contaRepository.findContaByCpf(cpf);
        contaRepository.delete(conta);
    }


}
