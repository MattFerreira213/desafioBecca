package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Model.Cliente;
import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Repository.ClienteRepository;
import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Utils.Exceptions.ContaJaExistenteException;
import com.everis.desafioBanco.Utils.Exceptions.CpfNaoEncontradoException;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void cadastrarConta(Conta conta) {
        Conta dadosConta = new Conta();

        dadosConta.setAgencia(conta.getAgencia());
        dadosConta.setCpf(conta.getCpf());
        dadosConta.setNumeroDaConta(conta.getNumeroDaConta());
        dadosConta.setDigitoVerificador(conta.getDigitoVerificador());
        dadosConta.setTipoDaConta(conta.getTipoDaConta());
        dadosConta.setSaldo(conta.getSaldo());

        Optional<Cliente> verificarCpfDaConta = Optional.ofNullable(
                clienteRepository.findClienteByCpf(conta.getCpf()));

        Optional<Conta> verificarExistenciaDeConta = Optional.ofNullable(
                contaRepository.findContaByNumeroDaConta(dadosConta.getNumeroDaConta()));

        if (verificarCpfDaConta.isPresent() && !verificarExistenciaDeConta.isPresent()){
            contaRepository.save(dadosConta);
        } else {
            throw new ContaJaExistenteException("Número da conta em uso ou cpf não encontrado.");
        }

    }

    public List<Conta> listarConta(String cpf) {
        var contas = contaRepository.findContaByCpf(cpf);
        if (contas.size() > 0){
            for (Conta conta : contas) {
                conta.getCpf();
            }
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Conta de cpf %s não encontrado ou não existe.", cpf));
        }
        return contas;
    }

    public void deletarConta(String cpf) {
        var contas = contaRepository.findContaByCpf(cpf);

        if (contas.size() > 0){
            for (Conta conta : contas) {
                contaRepository.delete(conta);
            }
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Conta de cpf %s não encontrado ou não existe.", cpf));
        }
    }
}
