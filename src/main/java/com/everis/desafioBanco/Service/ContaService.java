package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Model.Cliente;
import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Repository.ClienteRepository;
import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Exceptions.ContaExistenteException;
import com.everis.desafioBanco.Exceptions.CpfNaoEncontradoException;
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

    public void cadastrarConta(Conta dadosDaConta) {

        dadosDaConta.setAgencia(dadosDaConta.getAgencia());
        dadosDaConta.setCpf(dadosDaConta.getCpf());
        dadosDaConta.setNumeroDaConta(dadosDaConta.getNumeroDaConta());
        dadosDaConta.setDigitoVerificador(dadosDaConta.getDigitoVerificador());
        dadosDaConta.setTipoDaConta(dadosDaConta.getTipoDaConta());
        dadosDaConta.setSaldo(dadosDaConta.getSaldo());
        dadosDaConta.setQuantidadeDeSaqueSemTaxa(5);

        Optional<Cliente> verificarCpfDaConta = Optional.ofNullable(
                clienteRepository.findClienteByCpf(dadosDaConta.getCpf()));

        Optional<Conta> verificarExistenciaDeConta = Optional.ofNullable(
                contaRepository.findContaByNumeroDaConta(dadosDaConta.getNumeroDaConta()));

        if (verificarCpfDaConta.isPresent() && !verificarExistenciaDeConta.isPresent()){
            contaRepository.save(dadosDaConta);
        } else {
            throw new ContaExistenteException("Número da conta em uso ou cpf não encontrado.");
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
