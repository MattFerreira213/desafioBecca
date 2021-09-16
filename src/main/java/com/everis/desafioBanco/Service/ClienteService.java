package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Model.Cliente;
import com.everis.desafioBanco.Repository.ClienteRepository;
import com.everis.desafioBanco.Utils.Exceptions.ClienteJaCadastradoException;
import com.everis.desafioBanco.Utils.Exceptions.CpfNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private static Optional<Cliente> verificarcadastroCliente;

    public void cadastrarCliente(Cliente cliente) {

        Cliente dadosCliente = new Cliente();
        dadosCliente.setNome(cliente.getNome());
        dadosCliente.setCpf(cliente.getCpf());
        dadosCliente.setTelefone(cliente.getTelefone());
        dadosCliente.setEndereco(cliente.getEndereco());

        verificarcadastroCliente = Optional.ofNullable(clienteRepository.findClienteByCpf(dadosCliente.getCpf()));

        if (!verificarcadastroCliente.isPresent()) {
            clienteRepository.save(dadosCliente);
        } else {
            throw new ClienteJaCadastradoException("Esse cliente já possui um cadastro");
        }
    }

    public Cliente listarCliente(String cpf) {
        var cliente = clienteRepository.findClienteByCpf(cpf);

        verificarcadastroCliente = Optional.ofNullable(cliente);

        if (verificarcadastroCliente.isPresent()) {
            return cliente;
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Cliente de cpf %s não encontrado ou não existe.", cpf));
        }
    }

    public Cliente atualizarCliente(Cliente dadosCliente, String cpf) {
        var dadosAtualcliente = clienteRepository.findClienteByCpf(cpf);
        BeanUtils.copyProperties(dadosCliente, dadosAtualcliente);

        verificarcadastroCliente = Optional.ofNullable(dadosAtualcliente);

        if (verificarcadastroCliente.isPresent()) {
            return clienteRepository.save(dadosAtualcliente);
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Cliente de cpf %s não encontrado ou não existe.", cpf));
        }
    }

    public void deletarCliente(String cpf) {
        var cliente = clienteRepository.findClienteByCpf(cpf);

        verificarcadastroCliente = Optional.ofNullable(cliente);

        if (verificarcadastroCliente.isPresent()) {
            clienteRepository.delete(cliente);
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Cliente de cpf %s não encontrado ou não existe.", cpf));
        }

    }
}
